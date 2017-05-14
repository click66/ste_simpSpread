package simpSpread.Operation;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests for "Cosine" operation
 *
 * @author C. Sirl
 */
public class CosineTest extends OperationTest
{
    @Test
    public void testConstruct()
    {
        Cosine cosine = construct();

        assertNotNull(cosine);
        assertTrue(cosine instanceof Operation);
    }

    @Test
    public void apply() throws Exception
    {
        Cosine cosine = construct();

        double[][] values = {
            { 0, 1 },
            { 0.001, 0.9999995000000417 },
            { 256, -3.907585434500222E11 },
            { 0.001, 0.9999995000000417 },
            { Integer.MAX_VALUE, -1.362218254741463E53 },
        };

        for(double[] value : values) {
            assertOperationApply(cosine, value[0], value[1]);
        }
    }

    protected Cosine construct()
    {
        return new Cosine();
    }
}
