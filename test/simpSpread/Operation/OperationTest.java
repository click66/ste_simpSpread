package simpSpread.Operation;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Abstract test case for operation tests
 *
 * @author C. Sirl
 */
public abstract class OperationTest
{
    /**
     * Perform an operation and verify the result
     *
     * @param operation      The operation to perform
     * @param value1         First value
     * @param value2         Second value
     * @param expectedResult Expected result
     */
    protected void assertOperationApply(
        Operation operation,
        double value1,
        double value2,
        double expectedResult
    ) {
        Stack<Double> RPNStack = mock(Stack.class);
        when(RPNStack.pop()).thenReturn(value2).thenReturn(value1);

        operation.apply(RPNStack);

        verify(RPNStack, times(1)).push(expectedResult);
    }

    /**
     * Alternative method specifying only one value
     * @param operation      The operation to perform
     * @param value          The value
     * @param expectedResult Expected result
     */
    protected void assertOperationApply(
        Operation operation,
        double value,
        double expectedResult
    ) {
        assertOperationApply(operation, 0, value, expectedResult);
    }
}