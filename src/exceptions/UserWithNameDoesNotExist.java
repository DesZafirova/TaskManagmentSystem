package exceptions;

public class UserWithNameDoesNotExist extends RuntimeException{
    public UserWithNameDoesNotExist(String message) {
        super(message);
    }
}
