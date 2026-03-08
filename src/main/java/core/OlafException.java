package core;

/**
 * Represents a custom exception specific to the Olaf chatbot.
 * Thrown when the chatbot encounters an error in logic, parsing, or user input.
 */
public class OlafException extends Exception {
    /**
     * Constructs a new OlafException with the specified error message.
     *
     * @param message The detail message explaining the specific error to the user.
     */
    public OlafException(String message) {
        super((message));
    }
}
