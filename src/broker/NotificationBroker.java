package broker;


import exceptions.EventQueueFullException;
import exceptions.InvalidNotificationEventException;
import model.NotificationEvent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NotificationBroker implements INotificationBroker{
    private final BlockingQueue<NotificationEvent> queue;

    public NotificationBroker(int capacity) {
        if(capacity<=0){
            throw  new IllegalArgumentException("Capacity cannot be <=0");
        }
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void publish(NotificationEvent notificationEvent) throws EventQueueFullException {
       boolean accepted = queue.offer(notificationEvent);

       if(!accepted){
           throw  new EventQueueFullException("Queue is full");
       } else {
           System.out.println("Notification event published");
       }
    }

    @Override
    public NotificationEvent consume() throws InterruptedException {
        // take -> waits indefinitely until queue has an element, poll would return null and the caller
        // would have to implement additional logic for continuously polling
        return queue.take();
    }
}
