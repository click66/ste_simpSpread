package simpSpread.Operation;

import java.util.Stack;

/**
 * Subtract operator
 *
 * @author C. Sirl
 */
public class Subtract implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        RPNStack.push(- RPNStack.pop() + RPNStack.pop());
        return RPNStack;
    }
}
