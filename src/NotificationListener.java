import broker.NotificationBroker;
import model.Notification;
import model.NotificationEvent;

public class NotificationListener implements Runnable{
    private final NotificationBroker broker;
    private final NotificationEventProcessor eventProcessor;
    public NotificationListener(NotificationBroker broker, NotificationEventProcessor eventProcessor) {
        if(broker == null || eventProcessor == null){
            throw new IllegalArgumentException("Broker and event processor cannot be null");
        }
        this.broker = broker;
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void run(){
        while (!Thread.currentThread().isInterrupted()){
            try {
                NotificationEvent event = broker.consume();
                eventProcessor.process(event);
            }catch(InterruptedException e){
                System.out.println("Exception while consuming event from broker: "+e.getMessage());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
