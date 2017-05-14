//package simpSpread.Operation;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
///**
// * Tests for "Decrement" operation
// *
// * @author C. Sirl
// */
//public class DecrementTest extends OperationTest
//{
//    @Test
//    public void testConstruct()
//    {
//        Decrement decrement = construct();
//
//        assertNotNull(decrement);
//        assertTrue(decrement instanceof Operation);
//    }
//
//    @Test
//    public void apply() throws Exception
//    {
//        Decrement decrement = construct();
//
//        double[][] values = {
//            { 1, 0 },
//            { 2, 1 },
//            { 11, 10 },
//            { 100, 99 },
//            { (double)Integer.MAX_VALUE+1, (double)Integer.MAX_VALUE },
//            { Double.MAX_VALUE+1, Double.MAX_VALUE },
//            { 0, -1 },
//            { -1, -2 },
//            { -9, -10 },
//        };
//
//        for(double[] value : values) {
//            assertOperationApply(decrement, value[0], value[1]);
//        }
//    }
//
//    protected Decrement construct()
//    {
//        return new Decrement();
//    }
//}
