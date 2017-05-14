package simpSpread.Exception;

/**
 * Exception class for invalid arguments
 *
 * @author C. Sirl
 */
public class UnsupportedOperator extends IllegalArgumentException {
    public UnsupportedOperator() {
    }

    public UnsupportedOperator(String message) {
        super(message);
    }
}
