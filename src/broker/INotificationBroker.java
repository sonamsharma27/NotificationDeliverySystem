package broker;

import exceptions.EventQueueFullException;
import model.Notification;
import model.NotificationEvent;

public interface INotificationBroker {
    void publish(NotificationEvent notificationEvent) throws EventQueueFullException;
    NotificationEvent consume() throws InterruptedException;
}
