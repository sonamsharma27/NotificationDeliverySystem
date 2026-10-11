import deliveryTargets.IDeliveryTarget;
import enums.Enums;
import exceptions.EventQueueFullException;
import exceptions.InvalidNotificationEventException;
import model.DeliveryResult;
import model.Notification;
import model.NotificationEvent;
import service.NotificationDeliveryExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class NotificationEventProcessor {
    private final NotificationService notificationService;
    private final NotificationDeliveryExecutor executor;
    private final DeliveryStatusTracker tracker;

    public NotificationEventProcessor(NotificationService notificationService, NotificationDeliveryExecutor executor, DeliveryStatusTracker tracker) {
        if (notificationService == null) {
            throw new IllegalArgumentException("Notification service cannot be null");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Executor cannot be null");
        }
        if(tracker == null){
            throw new IllegalArgumentException("DeliveryStatusTracker cannot be null");
        }
        this.notificationService = notificationService;
        this.executor = executor;
        this.tracker = tracker;
    }

    public void process(NotificationEvent event) {
        if (event == null) {
            throw new InvalidNotificationEventException("Received null NotificationEvent");
        }
        List<IDeliveryTarget> targets = event.getDeliveryTargetList();
        Notification notification = event.getNotification();
        List<CompletableFuture<DeliveryResult>> futures = new ArrayList<>();
        for (IDeliveryTarget target : targets) {
            String deliveryId = UUID.randomUUID().toString();
            try {
                executor.submit(() -> notificationService.sendNotification(notification, target)).whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.err.println("Delivery task failed for target: " + target);
                    } else if (result == null) {
                        System.err.println("Delivery returned null result for target: " + target);
                    } else {
                        tracker.recordResult(deliveryId,result);
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("Notification delivery failed to target :" + target.toString() + " " + e.getMessage());
                System.out.println("Delivery id: "+ deliveryId);
            }
        }
        //futures can be used to create a delivery tracker
    }
}
