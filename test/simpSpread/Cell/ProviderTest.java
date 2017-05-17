package simpSpread.Cell;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import simpSpread.Exception.CannotRead;
import simpSpread.Exception.Exception;
import simpSpread.Exception.InvalidValue;
import simpSpread.InputScanner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Tests for "Cell.Provider" class
 *
 * @author C. Sirl
 */
public class ProviderTest
{
    private InputScanner inputScanner;
    private LinkedList<Cell> topologicalList;
    private HashMap<Integer, HashSet<Cell>> dependenciesMap;

    @Before
    public void setUp() throws Exception
    {
        inputScanner    = mock(InputScanner.class);
        topologicalList = new LinkedList<Cell>();
        dependenciesMap = new HashMap<Integer, HashSet<Cell>>();
    }

    @Test
    public void testConstruct() throws Exception
    {
        Provider provider = constructTest();

        assertNotNull(provider);
    }

    @Test
    public void getRowCount() throws Exception
    {
        // Test a variety of row counts
        int[] values = { 0, 1, 10, 100, 999 };

        for (int value : values) {
            // Mock the input parameters
            mockInput(1, value);

            Provider provider = constructTest();

            assertEquals(value, provider.getRowCount());
        }
    }

    @Test(expected=InvalidValue.class)
    public void getInvalidRowCount() throws Exception
    {
        mockInput(1, -1);

        Provider provider = constructTest();

        provider.getRowCount();
    }

    @Test
    public void getColumnCount() throws Exception
    {
        // Test a variety of column counts
        int[] values = { 0, 1, 10, 100, 999 };

        for (int value : values) {
            // Mock the input parameters
            mockInput(value, 1);

            Provider provider = constructTest();

            assertEquals(value, provider.getColumnCount());
        }
    }

    @Test(expected=InvalidValue.class)
    public void getInvalidColumnCount() throws Exception
    {
        mockInput(-1, 1);

        Provider provider = constructTest();

        provider.getColumnCount();
    }

    @Test
    public void getCellMatrix() throws Exception
    {
        // Test a variety of cell matrix sizes
        int[][] values = {
            { 2, 2 },
            { 5, 5 },
            { 1, 10 },
            { 10, 1 },
            { 99, 99 },
            { 99, 101}
        };

        for (int[] value : values) {
            int column = value[0];
            int row    = value[1];

            mockInput(column, row);

            Provider provider = constructTest();

            Cell[][] cellMatrix = provider.getCellMatrix();

            assertEquals(row, cellMatrix.length);
            for (int i = 0; i < cellMatrix.length; i++) {
                assertEquals(column, cellMatrix[i].length);
            }
        }
    }

    @Test
    public void getTopologicalList() throws Exception
    {
        mockInput(2, 2);

        Provider provider = constructTest();

        assertEquals(LinkedList.class, provider.getTopologicalList().getClass());
    }

    @Test
    public void getDependenciesMap() throws Exception
    {
        mockInput(2, 2);

        Provider provider = constructTest();

        assertEquals(HashMap.class, provider.getDependenciesMap().getClass());
    }

    private Provider constructTest()
    {
        return new Provider(
            inputScanner,
            topologicalList,
            dependenciesMap
        );
    }

    /**
     * Set the InputScanner mock to act as if a specific input was made
     *
     * @param columnCount Column count (integer)
     * @param rowCount    Row count (integer)
     * @param restOfInput All other lines of input
     */
    private void mockInput(int columnCount, int rowCount, String... restOfInput) throws CannotRead
    {
        // Mock the input parameters
        when(inputScanner.nextInt()).thenReturn(columnCount).thenReturn(rowCount);

        int expectedLines = columnCount * rowCount;
        if (expectedLines > 0) {
            OngoingStubbing<String> lineReturns = when(inputScanner.nextLine());

            for (String line : restOfInput) {
                lineReturns = lineReturns.thenReturn(line);
                expectedLines--;
            }

            while (expectedLines > 0) {
                lineReturns = lineReturns.thenReturn("1");
                expectedLines--;
            }
        }
    }
}