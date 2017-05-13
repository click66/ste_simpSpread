package simpSpread.Exception;

/**
 * Exception class for circular dependency
 *
 * @author A. Student
 */
public class NoInputToEvaluate extends Exception {
	public NoInputToEvaluate() {
	}

	public NoInputToEvaluate(String message) {
		super(message);
	}
}
