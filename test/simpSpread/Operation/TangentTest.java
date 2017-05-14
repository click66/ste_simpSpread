package simpSpread.Operation;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for "Tangent" operation
 *
 * @author C. Sirl
 */
public class TangentTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Tangent tangent = construct();

        assertNotNull(tangent);
        assertTrue(tangent instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Tangent tangent = construct();

        double[][] values = {
            { 0, 0 },
            { 0.001, 0.0010000003333334668 },
            { 256, 25.111559463448298 },
            { Integer.MAX_VALUE, 1.0523779637351338 },
        };

        for(double[] value : values) {
            assertOperationApply(tangent, value[0], value[1]);
        }
    }

    protected Tangent construct()
    {
        return new Tangent();
    }
}
