package simpSpread.Operation;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for "Subtract" operation
 *
 * @author C. Sirl
 */
public class SubstractTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Subtract subtract = construct();

        assertNotNull(subtract);
        assertTrue(subtract instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Subtract subtract = construct();

        double[][] values = {
            { 10, 5, 5 },
            { 1, 1, 0 },
            { 99, 99, 0 },
            { 1, 99, -98 },
            { 99, 1, 98 },
            { -1, -1, 0 },
            { -1, 1, -2 },
            { 0, 101, -101 }
        };

        for(double[] value : values) {
            assertOperationApply(subtract, value[0], value[1], value[2]);
        }
    }

    protected Subtract construct()
    {
        return new Subtract();
    }
}
