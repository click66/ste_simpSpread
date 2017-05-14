package simpSpread.Operation;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for "Increment" operation
 *
 * @author C. Sirl
 */
public class IncrementTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Increment increment = construct();

        assertNotNull(increment);
        assertTrue(increment instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Increment increment = construct();

        double[][] values = {
            { 0, 1 },
            { 1, 2 },
            { 10, 11 },
            { 99, 100 },
            { Integer.MAX_VALUE, (double)Integer.MAX_VALUE+1 },
            { Double.MAX_VALUE, Double.MAX_VALUE+1 },
            { -1, 0 },
            { -2, -1 },
            { -10, -9 },
        };

        for(double[] value : values) {
            assertOperationApply(increment, value[0], value[1]);
        }
    }

    protected Increment construct()
    {
        return new Increment();
    }
}
