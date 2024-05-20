package exceptions;

public class EntityAlreadyExistException extends RuntimeException{
     public static final String ALREADY_EXIST_ERROR = "%s with name %s already exist in the Database.%n";
    public EntityAlreadyExistException(String entityName, String providedName) {
        super(ALREADY_EXIST_ERROR.formatted(entityName, providedName));
    }
}
