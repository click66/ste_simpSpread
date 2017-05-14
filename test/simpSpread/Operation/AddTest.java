package simpSpread.Operation;

import org.junit.Test;

import java.nio.DoubleBuffer;
import java.util.Stack;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for "Add" operation
 *
 * @author C. Sirl
 */
public class AddTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Add add = construct();

        assertNotNull(add);
        assertTrue(add instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Add add = construct();

        double[][] values = {
            { 1, 1, 2 },
            { 5, 5, 10},
            { 99, 1, 100 },
            { 99, 99, 198 },
            { 0, 1, 1 },
            { -1, -1, -2 },
            { -1, 1, 0 },
            { -99, 10, -89 },
            { 5.5, 5.5, 11 },
            { 3.14, 15.9265, 19.0665 },
            { Integer.MAX_VALUE, 1, (double)Integer.MAX_VALUE+1 },
            { Double.MAX_VALUE, 1000, Double.MAX_VALUE+1000 }
        };

        for(double[] value : values) {
            assertOperationApply(add, value[0], value[1], value[2]);
        }
    }

    protected Add construct()
    {
        return new Add();
    }
}
