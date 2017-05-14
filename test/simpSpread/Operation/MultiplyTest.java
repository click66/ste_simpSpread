package simpSpread.Operation;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for "Multiply" operation
 *
 * @author C. Sirl
 */
public class MultiplyTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Multiply multiply = construct();

        assertNotNull(multiply);
        assertTrue(multiply instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Multiply multiply = construct();

        double[][] values = {
            { 1, 1, 1 },
            { 2, 2, 4 },
            { 5, 5, 25 },
            { 379 , 13, 4927 },
            { -5, 3, -15 },
            { 999999, 999999, 999998000001.0 }
        };

        for(double[] value : values) {
            assertOperationApply(multiply, value[0], value[1], value[2]);
        }
    }

    protected Multiply construct()
    {
        return new Multiply();
    }
}
