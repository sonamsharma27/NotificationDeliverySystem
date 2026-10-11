package publisher;

import exceptions.EventQueueFullException;
import model.NotificationEvent;

public interface INotificationPublisher {
    void publish(NotificationEvent notificationEvent) throws EventQueueFullException;
}
