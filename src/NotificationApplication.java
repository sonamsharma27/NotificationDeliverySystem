import broker.NotificationBroker;
import channel.SMSChannel;
import deliveryTargets.IDeliveryTarget;
import enums.Enums.ChannelType;
import enums.Enums.Status;
import model.Content;
import model.DeliveryResult;
import model.Notification;
import model.NotificationEvent;
import model.Recipient;
import provider.SmsProvider;
import publisher.NotificationPublisher;
import registry.ChannelRegistry;
import service.NotificationDeliveryExecutor;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class NotificationApplication {

    public static void main(String[] args) throws InterruptedException {

        // 1. Create the broker
        NotificationBroker broker = new NotificationBroker(100);

        // 2. Create a mock provider for the demo
        CountDownLatch deliveryFinished = new CountDownLatch(1);

        SmsProvider mockSmsProvider = sms -> {
            try {
                System.out.println(
                        "Sending SMS to " + sms.getMobileNumber()
                                + ": " + sms.getBody()
                );

                return new DeliveryResult(
                        Status.SUCCESS,
                        null,
                        null,
                        "demo-provider-reference"
                );
            } finally {
                deliveryFinished.countDown();
            }
        };

        // 3. Register the SMS channel
        ChannelRegistry registry = new ChannelRegistry();
        registry.addChannel(
                ChannelType.SMS,
                new SMSChannel(mockSmsProvider)
        );

        // 4. Create the notification service
        NotificationService notificationService =
                new NotificationService(registry);

        // 5. Create the delivery executor
        NotificationDeliveryExecutor executor =
                new NotificationDeliveryExecutor(2, 4, 100);

        DeliveryStatusTracker tracker  = new DeliveryStatusTracker();
        // 6. Create the event processor
        NotificationEventProcessor processor =
                new NotificationEventProcessor(
                        notificationService,
                        executor,
                        tracker
                );

        // 7. Start the listener
        NotificationListener listener =
                new NotificationListener(broker, processor);

        Thread listenerThread =
                new Thread(listener, "notification-listener");

        listenerThread.start();

        try {
            // 8. Create a notification
            Notification notification = new Notification(
                    new Content(
                            "Your order has been shipped!",
                            "Order Update",
                            List.of()
                    ),
                    Map.of(),
                    new Recipient("9876543210")
            );

            // 9. Specify the delivery channel
            IDeliveryTarget smsTarget =
                    () -> ChannelType.SMS;

            // 10. Create and publish the event
            NotificationEvent event = new NotificationEvent(
                    UUID.randomUUID().toString(),
                    notification,
                    List.of(smsTarget)
            );
            NotificationPublisher publisher = new NotificationPublisher(broker);
//            broker.publish(event);
            publisher.publish(event);
            // Wait only in this demo to observe completion.
            // Producers do not need to wait for delivery.
            boolean completed =
                    deliveryFinished.await(5, TimeUnit.SECONDS);

            if (!completed) {
                System.err.println("Timed out waiting for SMS delivery");
            }

        } finally {
            // Stop the listener and initiate executor shutdown.
            listenerThread.interrupt();

            try {
                listenerThread.join(1000);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }

            executor.shutdown();
        }
    }
}

