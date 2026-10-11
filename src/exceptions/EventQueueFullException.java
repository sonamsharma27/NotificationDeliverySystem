package exceptions;

public class EventQueueFullException extends InterruptedException {
    public EventQueueFullException(String message) {
        super(message);
    }
}
