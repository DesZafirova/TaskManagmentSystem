package exceptions;

public class TaskWithIdDoesNotExist extends RuntimeException{
    public TaskWithIdDoesNotExist(String message) {
        super(message);
    }
}
