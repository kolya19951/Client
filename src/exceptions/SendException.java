package exceptions;

/**
 * Created by Коля on 27.06.2015.
 */
public class SendException extends RuntimeException {
    @Override
    public String getMessage() {
        return "User tries to send empty string " + super.getMessage();
    }
}
