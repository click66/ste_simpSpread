package simpSpread;

/**
 * Class to represent Operators as Tokens
 *
 * @author A. Student
 */
public class OperatorToken extends Token {

	public OperatorToken(Operators operator) {
		setToken(operator.getOperator());
	}

	public Operators getParsedValue() {
		return Operators.get(getToken());
	}

}
