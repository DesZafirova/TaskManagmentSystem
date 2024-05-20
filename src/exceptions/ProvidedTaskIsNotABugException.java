package exceptions;

public class ProvidedTaskIsNotABugException extends RuntimeException{

    public static final String PROVIDED_TASK_WITH_ID_D_IS_NOT_OF_TYPE_BUG_N = "Provided task with id %d is not of type Bug.%n";

    public ProvidedTaskIsNotABugException(int id) {
        super(PROVIDED_TASK_WITH_ID_D_IS_NOT_OF_TYPE_BUG_N.formatted(id));
    }
}
