package simpSpread.Evaluator;

import org.junit.Test;
import simpSpread.Cell.Cell;
import simpSpread.Evaluator.WorkBook;
import simpSpread.Exception.Exception;

import java.util.HashMap;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for workBookEvaluator class
 *
 * @author C. Sirl
 */
public class WorkBookTest
{
    @Test
    public void testConstruct() throws Exception
    {
        WorkBook evaluator = new simpSpread.Evaluator.WorkBook(mock(simpSpread.Evaluator.Cell.class));

        assertNotNull(evaluator);
    }

    @Test
    public void evaluate() throws Exception
    {

    }

    @Test
    public void evaluateCircularDependency() throws Exception
    {
/*        // If size of topological list differs from cell count, it means circular dependency
        int cellCount              = 10;
        Cell[][] cellMatrix        = new Cell[5][5];
        HashMap dependenciesMap    = mock(HashMap.class);
        LinkedList topologicalList = mock(LinkedList.class);
        Cell cell                  = mock(Cell.class);

        when(topologicalList.size()).thenReturn(8); // Different size to cell count
        when(topologicalList.removeFirst()).thenReturn(cell);
        dependenciesMap.put(5, cell);

        WorkBook evaluator = new WorkBook(mock(simpSpread.Evaluator.Cell.class));

        evaluator.evaluate(cellCount, cellMatrix, dependenciesMap, topologicalList);*/
    }
}
