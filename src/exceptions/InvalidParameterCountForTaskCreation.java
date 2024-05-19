package exceptions;

public class InvalidParameterCountForTaskCreation extends RuntimeException{
    public InvalidParameterCountForTaskCreation(String message) {
        super(message);
    }
}
