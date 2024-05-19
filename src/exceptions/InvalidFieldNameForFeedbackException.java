package exceptions;

public class InvalidFieldNameForFeedbackException extends RuntimeException{
    public InvalidFieldNameForFeedbackException(String message) {
        super(message);
    }
}
