package exceptions;

public class NoBoardInTeamException extends RuntimeException{
    public static final String ERROR_MSG = "No board with name %s found in team %s.%n";
    public NoBoardInTeamException(String boardName, String teamName) {
        super(ERROR_MSG.formatted(boardName,teamName));
    }
}
