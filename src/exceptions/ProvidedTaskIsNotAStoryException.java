package exceptions;

public class ProvidedTaskIsNotAStoryException extends RuntimeException{

    public static final String PROVIDED_TASK_WITH_ID_D_IS_NOT_OF_TYPE_STORY_N = "Provided task with id %d is not of type Story.%n";

    public ProvidedTaskIsNotAStoryException(int id) {
        super(PROVIDED_TASK_WITH_ID_D_IS_NOT_OF_TYPE_STORY_N.formatted(id));
    }
}
