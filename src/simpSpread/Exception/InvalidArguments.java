package simpSpread.Exception;

/**
 * Exception class for invalid arguments
 *
 * @author C. Sirl
 */
public class InvalidArguments extends Exception {
    public InvalidArguments() {
    }

    public InvalidArguments(String message) {
        super(message);
    }
}
