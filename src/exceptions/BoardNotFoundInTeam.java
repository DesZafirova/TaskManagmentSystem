package exceptions;

public class BoardNotFoundInTeam extends RuntimeException{
    public BoardNotFoundInTeam(String message) {
        super(message);
    }
}
