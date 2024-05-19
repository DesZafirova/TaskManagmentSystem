package exceptions;

public class ProvidedTaskIsNotABugException extends RuntimeException{
    public ProvidedTaskIsNotABugException(String message) {
        super(message);
    }
}
