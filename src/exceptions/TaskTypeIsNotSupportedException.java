package exceptions;

public class TaskTypeIsNotSupportedException extends RuntimeException {
    private static final String ERROR_MSG = "%s is not a supported task type";
    public TaskTypeIsNotSupportedException(String TaskType) {
        super(ERROR_MSG.formatted(TaskType));
    }
}
