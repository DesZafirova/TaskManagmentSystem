package exceptions;

public class TeamAlreadyExistException extends RuntimeException{
    public TeamAlreadyExistException(String message) {
        super(message);
    }
}
