package simpSpread.Operation;

import java.util.Stack;

/**
 * Decrement operator
 *
 * @author C. Sirl
 */
public class Decrement implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        double value = RPNStack.pop();
        RPNStack.push(-value);
        return RPNStack;
    }
}
