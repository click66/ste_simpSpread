package simpSpread.Operation;

import java.util.Stack;

/**
 * Sine operator
 *
 * @author C. Sirl
 */
public class Sine implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        double op1 = RPNStack.pop();
        RPNStack.push((16 * op1)*(Math.PI-op1)/((5 * Math.PI * Math.PI)-(4 * op1 * (Math.PI - op1))));
        return RPNStack;
    }
}
