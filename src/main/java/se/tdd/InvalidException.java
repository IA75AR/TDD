package se.tdd;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
