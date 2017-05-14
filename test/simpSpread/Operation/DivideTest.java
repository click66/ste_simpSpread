package simpSpread.Operation;

import org.junit.Test;
import simpSpread.Exception.DivideByZeroException;
import simpSpread.Exception.Exception;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for "Divide" operation
 *
 * @author C. Sirl
 */
public class DivideTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Divide divide = construct();

        assertNotNull(divide);
        assertTrue(divide instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Divide divide = construct();

        double[][] values = {
            { 1, 1, 1 },
            { 1, 2, 0.5 },
            { 50, 25, 2 },
            { 25, 50, 0.5 },
            { -5, 2, -2.5 },
            { Integer.MAX_VALUE, Integer.MAX_VALUE, 1 },
            { Double.MAX_VALUE, Double.MAX_VALUE, 1 }
        };

        for(double[] value : values) {
            assertOperationApply(divide, value[0], value[1], value[2]);
        }
    }

    @Test(expected=DivideByZeroException.class)
    public void applyDivideByZero() throws Exception
    {
        Divide divide = construct();

        assertOperationApply(divide, 10, 0, 0);
    }

    protected Divide construct()
    {
        return new Divide();
    }
}
