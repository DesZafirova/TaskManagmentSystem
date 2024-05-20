package exceptions;

public class InvalidFieldNameForBugException extends RuntimeException{
    public static final String INVALID_FIELD_ERROR = "No field with name %s for tasks of type %s.%n";
    public InvalidFieldNameForBugException(String fieldName, String realName) {
        super(INVALID_FIELD_ERROR.formatted(fieldName, realName));
    }
}
