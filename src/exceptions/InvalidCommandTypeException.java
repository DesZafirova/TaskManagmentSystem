package exceptions;

public class InvalidCommandTypeException extends RuntimeException{
    public InvalidCommandTypeException(String message) {
        super(message);
    }
}
