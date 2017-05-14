//package simpSpread.Operation;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
///**
// * Tests for "Sine" operation
// *
// * @author C. Sirl
// */
//public class SineTest extends OperationTest
//{
//    @Test
//    public void testConstruct()
//    {
//        Sine sine = construct();
//
//        assertNotNull(sine);
//        assertTrue(sine instanceof Operation);
//    }
//
//    @Test
//    public void apply() throws Exception
//    {
//        Sine sine = construct();
//
//        double[][] values = {
//            { 0, 0 },
//            { 256, -0.9992080341070627 },
//            { 0.001, 9.999998333333417E-4 },
//            { Integer.MAX_VALUE, -0.7249165551445564 },
//        };
//
//        for(double[] value : values) {
//            assertOperationApply(sine, value[0], value[1]);
//        }
//    }
//
//    protected Sine construct()
//    {
//        return new Sine();
//    }
//}
