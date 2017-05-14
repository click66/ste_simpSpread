package simpSpread.Operation;

import java.util.Stack;

/**
 * Increment operator
 *
 * @author C. Sirl
 */
public class Increment implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        double op1 = RPNStack.pop();
        RPNStack.push(++op1);
        return RPNStack;
    }
}
