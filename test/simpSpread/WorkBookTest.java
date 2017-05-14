package simpSpread;

import org.junit.Test;
import simpSpread.Cell.Cell;
import simpSpread.Cell.Provider;
import simpSpread.Evaluator.WorkBook;
import simpSpread.Exception.Exception;
import simpSpread.Exception.NoInputToEvaluate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for Workbook test
 *
 * @author C. Sirl
 */
public class WorkBookTest
{
    @Test
    public void testConstruct() throws Exception
    {
        simpSpread.WorkBook workBook = new simpSpread.WorkBook(mock(Provider.class), mock(simpSpread.Evaluator.WorkBook.class));

        assertNotNull(workBook);
    }
    @Test
    public void testCircularDependent() throws Exception
    {
        simpSpread.WorkBook workBook = new simpSpread.WorkBook(mock(Provider.class), mock(simpSpread.Evaluator.WorkBook.class));

        assertFalse(workBook.isCircularDependent());

        workBook.setCircularDependent(true);

        assertTrue(workBook.isCircularDependent());

        workBook.setCircularDependent(false);

        assertFalse(workBook.isCircularDependent());
    }

    @Test
    public void readInput() throws Exception
    {
        Provider provider = mock(Provider.class);

        simpSpread.WorkBook workBook = new simpSpread.WorkBook(provider, mock(simpSpread.Evaluator.WorkBook.class));

        workBook.readInput();

        verify(provider, times(1)).getCellMatrix();
        verify(provider, times(1)).getDependenciesMap();
        verify(provider, times(1)).getTopologicalList();
        verify(provider, times(1)).getColumnCount();
        verify(provider, times(1)).getRowCount();
    }

    /**
     * Test that calling evaluate without first calling readInput() throws an exception
     */
    @Test(expected=NoInputToEvaluate.class)
    public void evaluateWithoutRead() throws Exception
    {
        Provider provider = mock(Provider.class);

        simpSpread.WorkBook workBook = new simpSpread.WorkBook(provider, mock(simpSpread.Evaluator.WorkBook.class));

        workBook.evaluate();
    }

    @Test
    public void evaluate() throws Exception
    {
        Cell[][] cellMatrix = new Cell[2][1];
        HashMap<Integer, HashSet<Cell>> dependenciesMap = new HashMap<Integer, HashSet<Cell>>();
        LinkedList<Cell> topologicalList = new LinkedList<Cell>();

        Provider provider = mock(Provider.class);
        when(provider.getColumnCount()).thenReturn(2);
        when(provider.getRowCount()).thenReturn(1);
        when(provider.getCellMatrix()).thenReturn(cellMatrix);
        when(provider.getTopologicalList()).thenReturn(topologicalList);
        when(provider.getDependenciesMap()).thenReturn(dependenciesMap);

        simpSpread.Evaluator.WorkBook evaluator =  mock(WorkBook.class);

        simpSpread.WorkBook workBook = new simpSpread.WorkBook(provider, evaluator);

        workBook.readInput();
        workBook.evaluate();

        verify(evaluator, times(1)).evaluate(
            2,
            cellMatrix,
            dependenciesMap,
            topologicalList
        );
    }

    @Test
    public void printWorkbook() throws Exception
    {

    }

    @Test
    public void prettyPrintWorkbook() throws Exception
    {

    }
}