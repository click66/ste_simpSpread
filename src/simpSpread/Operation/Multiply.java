package simpSpread.Operation;

import java.util.Stack;

/**
 * Multiply operator
 *
 * @author C. Sirl
 */
public class Multiply implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        RPNStack.push(RPNStack.pop() * RPNStack.pop());
        return RPNStack;
    }
}
