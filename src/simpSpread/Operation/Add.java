package simpSpread.Operation;

import java.util.Stack;

/**
 * Add operator
 *
 * @author C. Sirl
 */
public class Add implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        RPNStack.push(RPNStack.pop() + RPNStack.pop());
        return RPNStack;
    }
}
