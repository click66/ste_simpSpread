package simpSpread.Operation;

import java.util.Stack;

/**
 * Cosine operator
 *
 * @author C. Sirl
 */
public class Cosine implements Operation
{
    @Override
    public Stack<Double> apply(Stack<Double> RPNStack)
    {
        double op1 = RPNStack.pop();
        double fastCos = 1 - (Math.pow(op1, 2.0)/2)+(Math.pow(op1, 4))/24-(Math.pow(op1,6)/720);
        RPNStack.push(fastCos);
        return RPNStack;
    }
}
