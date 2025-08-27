package base;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException() {
        super("Sorry, I don't know what you mean :(");
    }

    public InvalidInputException(String message) {
        super(message);
    }
}
