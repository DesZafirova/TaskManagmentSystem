package exceptions;

public class ProvidedTaskIsNotAFeedbackException extends RuntimeException{
    public ProvidedTaskIsNotAFeedbackException(String message) {
        super(message);
    }
}
