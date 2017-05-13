package simpSpread.Token;

import simpSpread.Token.Token;

/**
 * Class to represent Values(operands) as Tokens
 *
 * @author A. Student
 */
public class ValueToken extends Token
{

	public static final String valuePatternRegex = "^[0-9]\\d*(\\.\\d+)?$"; /* was "[+-]?\\d+"; */

	public ValueToken(String str) {
		setToken(str);
	}

	public double getParsedValue() {
		return Double.parseDouble(getToken());
	}
}
