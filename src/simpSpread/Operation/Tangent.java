package simpSpread.Operation;

import java.util.Stack;

/**
 * Tangent operator
 *
 * @author C. Sirl
 */
public class Tangent implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        RPNStack.push(Math.tan(RPNStack.pop()));
        return RPNStack;
    }
}
