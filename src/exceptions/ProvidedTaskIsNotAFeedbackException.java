package exceptions;

public class ProvidedTaskIsNotAFeedbackException extends RuntimeException{

    public static final String PROVIDED_TASK_WITH_ID_D_IS_NOT_OF_TYPE_FEEDBACK_N = "Provided task with id %d is not of type Feedback.%n";

    public ProvidedTaskIsNotAFeedbackException(int id) {
        super(PROVIDED_TASK_WITH_ID_D_IS_NOT_OF_TYPE_FEEDBACK_N.formatted(id));
    }
}
