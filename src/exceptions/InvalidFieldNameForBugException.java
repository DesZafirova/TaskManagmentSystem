package exceptions;

public class InvalidFieldNameForBugException extends RuntimeException{
    public InvalidFieldNameForBugException(String message) {
        super(message);
    }
}
