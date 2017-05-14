package simpSpread.Exception;

/**
 * Exception class for invalid arguments
 *
 * @author C. Sirl
 */
public class DivideByZeroException extends IllegalArgumentException {
    public DivideByZeroException() {
    }

    public DivideByZeroException(String message) {
        super(message);
    }
}
