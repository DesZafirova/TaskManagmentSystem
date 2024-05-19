package exceptions;

public class BoardAlreadyExist extends RuntimeException{
    public BoardAlreadyExist(String message) {
        super(message);
    }
}
