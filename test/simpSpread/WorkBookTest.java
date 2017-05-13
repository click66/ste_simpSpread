package simpSpread;

import org.junit.Test;
import simpSpread.Cell.Provider;
import simpSpread.Exception.CircularDependencyException;
import simpSpread.Exception.Exception;
import simpSpread.Exception.NoInputToEvaluate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by clark on 13/05/2017.
 */
public class WorkBookTest
{
    @Test
    public void testConstruct() throws Exception
    {
        WorkBook workBook = new WorkBook(
            mock(Provider.class)
        );

        assertNotNull(workBook);
    }
    @Test
    public void testCircularDependent() throws Exception
    {
        WorkBook workBook = new WorkBook(mock(Provider.class));

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

        WorkBook workBook = new WorkBook(provider);

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

        WorkBook workBook = new WorkBook(provider);

        workBook.evaluate();
    }

    //@Test(expected= CircularDependencyException.class)
    @Test
    public void evaluateCircularDependencyInjection() throws Exception
    {
        Provider provider = mock(Provider.class);
        when(provider.getColumnCount()).thenReturn(2);
        when(provider.getRowCount()).thenReturn(2);

        WorkBook workBook = new WorkBook(provider);

        workBook.readInput();
        workBook.evaluate();
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