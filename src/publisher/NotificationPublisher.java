package publisher;

import broker.INotificationBroker;
import exceptions.EventQueueFullException;
import exceptions.InvalidNotificationEventException;
import model.NotificationEvent;

public class NotificationPublisher implements  INotificationPublisher{
    private final INotificationBroker broker;
    public  NotificationPublisher(INotificationBroker broker){
        if(broker==null){
            throw new RuntimeException("Broker cannot be null");
        }
        this.broker=broker;
    }

    @Override
    public void publish(NotificationEvent notificationEvent) throws EventQueueFullException{
        validateNotificationEvent(notificationEvent);
        broker.publish(notificationEvent);
    }

    void validateNotificationEvent(NotificationEvent notificationEvent){
        if(notificationEvent == null || notificationEvent.getEventId() == null){
            throw  new InvalidNotificationEventException("Invalid notification event");
        }
        if(notificationEvent.getDeliveryTargetList() == null || notificationEvent.getNotification() == null){
            throw  new InvalidNotificationEventException("Invalid notification event");
        }
    }

}
