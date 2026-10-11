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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

public class NotificationEventProcessor {
    private final NotificationService notificationService;
    private final NotificationDeliveryExecutor executor;

    public NotificationEventProcessor(NotificationService notificationService, NotificationDeliveryExecutor executor) {
        if (notificationService == null) {
            throw new IllegalArgumentException("Notification service cannot be null");
        }
        if (executor == null) {
            throw new IllegalArgumentException("Executor cannot be null");
        }
        this.notificationService = notificationService;
        this.executor = executor;
    }

    public void process(NotificationEvent event) {
        if (event == null) {
            throw new InvalidNotificationEventException("Received null NotificationEvent");
        }
        List<IDeliveryTarget> targets = event.getDeliveryTargetList();
        Notification notification = event.getNotification();
        List<CompletableFuture<DeliveryResult>> futures = new ArrayList<>();
        for (IDeliveryTarget target : targets) {
            try {
                executor.submit(() -> notificationService.sendNotification(notification, target)).whenComplete((result, exception) -> {
                    if (exception != null) {
                        System.err.println("Delivery task failed for target: " + target);
                        exception.printStackTrace();
                    } else if (result == null) {
                        System.err.println("Delivery returned null result for target: " + target);
                    } else {
                        switch (result.getStatus()) {
                            case SUCCESS:
                                System.out.println("Delivery successful for target: " + target);
                                break;
                            case FAILED:
                                System.err.println("Delivery failed for target: " + target + ", errorCode: " + result.getErrorCode() + ", message: " + result.getErrorMessage());
                                break;
                            case UNKNOWN:
                                System.err.println("Delivery outcome unknown for target: " + target + ", providerReference: " + result.getProviderReference());
                                break;
                        }
                    }
                });
            } catch (RejectedExecutionException e) {
                System.out.println("Notification delivery failed to target :" + target.toString() + " " + e.getMessage());
                e.printStackTrace();
            }
        }
        //futures can be used to create a delivery tracker
    }
}
