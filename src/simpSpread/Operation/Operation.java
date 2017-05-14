package simpSpread.Operation;

import java.util.Stack;

/**
 * Generic operation interface for strategy pattern
 *
 * @author C. Sirl
 */
public interface Operation
{
    Stack<Double> apply(Stack<Double> RPNStack);
}
