package exceptions;

public class TaskHasNoAssigneeException extends RuntimeException{
    public TaskHasNoAssigneeException(String message) {
        super(message);
    }
}
