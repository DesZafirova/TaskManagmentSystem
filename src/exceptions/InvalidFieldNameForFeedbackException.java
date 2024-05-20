package exceptions;

public class InvalidFieldNameForFeedbackException extends RuntimeException{
    public static final String INVALID_FIELD_ERROR = "No field with name %s for tasks of type %s.%n";
    public InvalidFieldNameForFeedbackException(String fieldName, String realName) {
        super(INVALID_FIELD_ERROR.formatted(fieldName, realName));
    }
}
