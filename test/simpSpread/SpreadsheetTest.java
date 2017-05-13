package simpSpread;

import org.junit.Test;
import simpSpread.Exception.Exception;
import simpSpread.Exception.FileNotFound;

import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for Spreadsheet class
 */
public class SpreadsheetTest
{
    @Test
    public void testConstruct() throws Exception
    {
        Spreadsheet spreadsheet = new Spreadsheet(
            mock(WorkBook.class)
        );

        assertNotNull(spreadsheet);

        Spreadsheet spreadsheetPrettyPrint = new Spreadsheet(
            mock(WorkBook.class),
            true
        );

        assertNotNull(spreadsheetPrettyPrint);
    }

    @Test
    public void isWorkBookCircular() throws Exception
    {
        WorkBook circular = mock(WorkBook.class);
        when(circular.isCircularDependent()).thenReturn(true);

        WorkBook notCircular = mock(WorkBook.class);
        when(notCircular.isCircularDependent()).thenReturn(false);

        assertTrue((new Spreadsheet(circular)).isWorkBookCircular());

        assertFalse((new Spreadsheet(notCircular)).isWorkBookCircular());
    }

    @Test
    public void isPrettyPrint() throws Exception
    {
        Spreadsheet spreadsheetPrettyPrint = new Spreadsheet(
            mock(WorkBook.class),
            true
        );

        assertTrue(spreadsheetPrettyPrint.isPrettyPrint());

        Spreadsheet spreadsheetNotPrettyPrint = new Spreadsheet(
            mock(WorkBook.class),
            false
        );

        assertFalse(spreadsheetNotPrettyPrint.isPrettyPrint());

    }

    @Test
    public void processWorkbook() throws Exception
    {
        WorkBook workBook = mock(WorkBook.class);

        Spreadsheet spreadsheet = new Spreadsheet(workBook);

        spreadsheet.processWorkbook();

        verify(workBook, times(1)).readInput();
    }

    @Test
    public void evaluate() throws Exception
    {
        WorkBook workBook = mock(WorkBook.class);

        Spreadsheet spreadsheet = new Spreadsheet(workBook);

        spreadsheet.evaluate();

        verify(workBook, times(1)).evaluate();
    }

    @Test
    public void getResults() throws Exception
    {
        WorkBook workBook = mock(WorkBook.class);
        Spreadsheet spreadsheet = new Spreadsheet(workBook);

        spreadsheet.getResults();

        verify(workBook, times(1)).printWorkbook(true);
    }

    /**
     * Integration test
     */
    @Test
    public void getInputScanner() throws Exception, FileNotFound
    {
        assertEquals(
            InputScanner.class,
            Spreadsheet.getInputScanner(mock(InputStream.class), new String[0]).getClass()
        );
    }
}
