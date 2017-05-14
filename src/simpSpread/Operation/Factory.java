package simpSpread.Operation;

import simpSpread.Exception.UnsupportedOperator;
import simpSpread.Operators;
import simpSpread.Token.OperatorToken;

/**
 * Operations factory
 *
 * @author C. Sirl
 */
public class Factory
{
    public Operation getOperation(OperatorToken operatorToken)
    {
        switch (Operators.get(operatorToken.getToken())) {
            case ADD:
                return new Add();
            case SUB:
                return new Subtract();
            case MUL:
                return new Multiply();
            case DIV:
                return new Divide();
            case INC:
                return new Increment();
            case DEC:
                return new Decrement();
            case SIN:
                return new Sine();
            case COS:
                return new Cosine();
            case TAN:
                return new Tangent();
            default:
                throw new UnsupportedOperator(
                    "The passed operator " + operatorToken.getToken() + " is invalid or not supported."
                );
        }
    }
}
