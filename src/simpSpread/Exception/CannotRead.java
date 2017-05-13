package simpSpread.Exception;

/**
 * Exception class for circular dependency
 *
 * @author A. Student
 */
public class CannotRead extends Exception {
	public CannotRead() {
	}

	public CannotRead(String message) {
		super(message);
	}
}
