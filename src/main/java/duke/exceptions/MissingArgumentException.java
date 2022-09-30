package duke.exceptions;

public class MissingArgumentException extends Exception {
    private static final String MESSAGE = "Missing description. Please type again.";

    public String getMessage() {
        return MESSAGE;
    }
}
