package exceptions;

public class TeamWithNameDoesNotExist extends RuntimeException{
    public TeamWithNameDoesNotExist(String message) {
        super(message);
    }
}
