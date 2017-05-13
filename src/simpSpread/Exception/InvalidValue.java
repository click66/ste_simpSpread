package simpSpread.Exception;

/**
 * Exception class for invalid arguments
 *
 * @author C. Sirl
 */
public class InvalidValue extends Exception {
    public InvalidValue() {
    }

    public InvalidValue(String message) {
        super(message);
    }
}
