package simpSpread;

import org.junit.Test;

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
            mock(WorkBook.class),
            mock(InputScanner.class)
        );

        assertNotNull(spreadsheet);

        Spreadsheet spreadsheetPrettyPrint = new Spreadsheet(
            mock(WorkBook.class),
            mock(InputScanner.class),
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

        assertTrue((new Spreadsheet(circular, mock(InputScanner.class))).isWorkBookCircular());

        assertFalse((new Spreadsheet(notCircular, mock(InputScanner.class))).isWorkBookCircular());
    }

    @Test
    public void setInputScanner() throws Exception
    {
        Spreadsheet spreadsheet = new Spreadsheet(mock(WorkBook.class), mock(InputScanner.class));

        spreadsheet.setInputScanner(mock(InputScanner.class));
    }

    @Test
    public void isPrettyPrint() throws Exception
    {
        Spreadsheet spreadsheetPrettyPrint = new Spreadsheet(
            mock(WorkBook.class),
            mock(InputScanner.class),
            true
        );

        assertTrue(spreadsheetPrettyPrint.isPrettyPrint());

        Spreadsheet spreadsheetNotPrettyPrint = new Spreadsheet(
            mock(WorkBook.class),
            mock(InputScanner.class),
            false
        );

        assertFalse(spreadsheetNotPrettyPrint.isPrettyPrint());

    }

    @Test
    public void processWorkbook() throws Exception
    {
        WorkBook workBook = mock(WorkBook.class);
        InputScanner scanner = mock(InputScanner.class);

        Spreadsheet spreadsheet = new Spreadsheet(workBook, scanner);

        spreadsheet.processWorkbook();

        verify(workBook, times(1)).readInput(scanner);
        verify(scanner, times(1)).close();
    }

    @Test
    public void evaluate() throws Exception
    {
        WorkBook workBook = mock(WorkBook.class);
        InputScanner scanner = mock(InputScanner.class);

        Spreadsheet spreadsheet = new Spreadsheet(workBook, scanner);

        spreadsheet.evaluate();

        verify(workBook, times(1)).evaluate();
    }

    @Test
    public void getResults() throws Exception
    {
        WorkBook workBook = mock(WorkBook.class);
        Spreadsheet spreadsheet = new Spreadsheet(workBook, mock(InputScanner.class));

        spreadsheet.getResults();

        verify(workBook, times(1)).printWorkbook(true);
    }
}