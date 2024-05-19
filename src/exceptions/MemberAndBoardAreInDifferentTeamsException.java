package exceptions;

public class MemberAndBoardAreInDifferentTeamsException extends RuntimeException {
    public MemberAndBoardAreInDifferentTeamsException(String message) {
        super(message);
    }
}
