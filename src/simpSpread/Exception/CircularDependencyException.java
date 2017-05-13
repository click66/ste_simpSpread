package simpSpread.Exception;

/**
 * Exception class for circular dependency
 *
 * @author A. Student
 */
public class CircularDependencyException extends Exception {
	public CircularDependencyException() {
	}

	public CircularDependencyException(String message) {
		super(message);
	}
}
