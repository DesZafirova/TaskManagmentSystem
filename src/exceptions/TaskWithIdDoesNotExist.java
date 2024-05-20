package exceptions;

public class TaskWithIdDoesNotExist extends RuntimeException{
    public static final String TASK_DOES_NOT_EXIST = "Task with id: %d does not exist.";
    public TaskWithIdDoesNotExist(int id) {
        super(TASK_DOES_NOT_EXIST.formatted(id));
    }
}
