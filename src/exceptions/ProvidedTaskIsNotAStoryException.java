package exceptions;

public class ProvidedTaskIsNotAStoryException extends RuntimeException{
    public ProvidedTaskIsNotAStoryException(String message) {
        super(message);
    }
}
