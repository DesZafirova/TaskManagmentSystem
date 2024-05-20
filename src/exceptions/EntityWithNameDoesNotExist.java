package exceptions;

public class EntityWithNameDoesNotExist extends RuntimeException{
    public static final String ENTITY_DOES_NOT_EXIST = "%s with name %s not found in the Database.%n";
    public EntityWithNameDoesNotExist(String entityName, String providedName) {
        super(ENTITY_DOES_NOT_EXIST.formatted(entityName, providedName));
    }
}
