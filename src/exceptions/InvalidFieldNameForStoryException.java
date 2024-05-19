package exceptions;

public class InvalidFieldNameForStoryException extends RuntimeException{
    public InvalidFieldNameForStoryException(String message) {
        super(message);
    }
}
