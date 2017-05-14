package simpSpread;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Class to represent all the supported operators. All the operators and their logic is encapsulated here. To support
 * new operator define them here.
 *
 * @author A. Student
 */
public enum Operators {

	ADD("+"), SUB("-"), MUL("*"), DIV("/"), INC("++"), DEC("--"), SIN("S"), COS("C"), TAN("T");

	// Reverse-lookup map for getting a Operator from an operator string
	private static final Map<String, Operators> lookup = new HashMap<String, Operators>();

	static {
		for (Operators op : Operators.values())
			lookup.put(op.getOperator(), op);
	}

	private final String operator;

	private Operators(String op) {
		operator = op;
	}

	public static Operators get(String op) {
		return lookup.get(op);
	}

	public static boolean isValidOperator(String op) {
		return get(op) != null;
	}

	public String getOperator() {
		return operator;
	}
}