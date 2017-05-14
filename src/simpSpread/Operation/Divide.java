package simpSpread.Operation;

import simpSpread.Exception.DivideByZeroException;

import java.util.Stack;

/**
 * Divide operator
 *
 * @author C. Sirl
 */
public class Divide implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        double op1 = RPNStack.pop();
        double op2 = RPNStack.pop();
        if (op1 == 0) {
            throw new DivideByZeroException("Cannot divide by zero");
        }
        RPNStack.push(op2 / op1);
        return RPNStack;
    }
}
