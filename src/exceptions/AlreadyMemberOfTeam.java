package exceptions;

public class AlreadyMemberOfTeam extends RuntimeException{
    public AlreadyMemberOfTeam(String message) {
        super(message);
    }
}
