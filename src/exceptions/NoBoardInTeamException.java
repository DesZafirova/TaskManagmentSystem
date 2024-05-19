package exceptions;

public class NoBoardInTeamException extends RuntimeException{
    public NoBoardInTeamException(String message) {
        super(message);
    }
}
