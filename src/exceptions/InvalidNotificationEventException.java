package exceptions;

public class InvalidNotificationEventException extends RuntimeException {
    public InvalidNotificationEventException(String message) {
        super(message);
    }
}
