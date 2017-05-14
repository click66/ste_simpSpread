package simpSpread;

import simpSpread.Token.OperatorToken;
import simpSpread.Token.ReferenceToken;
import simpSpread.Token.Token;
import simpSpread.Token.ValueToken;

/**
 * Factory for getting appropriate Token from a given token String based on Factory Design Pattern
 *
 * @author A. Student
 */
public class TokenFactory {
	public Token makeToken(String str) throws RuntimeException {
		if (Operators.isValidOperator(str))
			return new OperatorToken(Operators.get(str));
		else if (str.matches(ReferenceToken.refPatternRegex))
			return new ReferenceToken(str);
		else if (str.matches(ValueToken.valuePatternRegex))
			return new ValueToken(str);
		else
			throw new RuntimeException("Error: Invalid token: " + str);
	}
}
