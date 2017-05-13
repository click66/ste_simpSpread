package simpSpread.Cell;

import simpSpread.Exception.CannotRead;
import simpSpread.Exception.InvalidValue;
import simpSpread.InputScanner;
import simpSpread.Token.ReferenceToken;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Cell Provider
 */
public class Provider
{
    private InputScanner inputScanner;
    private boolean read = false;

    private int rowCount;
    private int columnCount;

    private Cell[][] cellMatrix;
    private LinkedList<Cell> topologicalList;
    private HashMap<Integer, HashSet<Cell>> dependenciesMap;

    public Provider(
        InputScanner inputScanner,
        LinkedList<Cell> topologicalList,
        HashMap<Integer, HashSet<Cell>> dependenciesMap
    ) {
        this.inputScanner = inputScanner;
        this.topologicalList = topologicalList;
        this.dependenciesMap = dependenciesMap;
    }

    /**
     * Get row count
     *
     * @return int
     */
    public int getRowCount() throws CannotRead, InvalidValue
    {
        read();

        return rowCount;
    }

    /**
     * Get column count
     *
     * @return int
     */
    public int getColumnCount() throws CannotRead, InvalidValue
    {
        read();

        return columnCount;
    }

    /**
     * Get the cell matrix
     *
     * @return Cell[][]
     */
    public Cell[][] getCellMatrix() throws CannotRead, InvalidValue
    {
        read();

        return cellMatrix;
    }

    /**
     * Get the topological list
     *
     * @return LinkedList<Cell>
     */
    public LinkedList<Cell> getTopologicalList()
    {
        return topologicalList;
    }

    /**
     * Get the dependencies map
     *
     * @return HashMap<Integer, HashSet<Cell>>
     */
    public HashMap<Integer, HashSet<Cell>> getDependenciesMap()
    {
        return dependenciesMap;
    }

    /**
     * Read the inputs
     */
    private void read() throws CannotRead, InvalidValue
    {
        if (!read) {    // If not already read
            columnCount = inputScanner.nextInt();
            rowCount = inputScanner.nextInt();

            inputScanner.nextLine();    // omit the newline

            if (rowCount < 0 || columnCount < 0) {
                throw new InvalidValue("Invalid row count or column count specified.");
            }

            cellMatrix = new Cell[rowCount][columnCount];

            for (int row = 0; row < rowCount; row++) {
                for (int col = 0; col < columnCount; col++) {
                    String data = inputScanner.nextLine().trim().toUpperCase();
                    Cell curCell = cellMatrix[row][col] = new Cell(row, col, data);
                    if (curCell.getReferences().size() > 0) {
                        addToDependenciesMap(curCell);
                    } else {
                        topologicalList.add(curCell);
                    }
                }
            }

            inputScanner.close();

            this.read = true;
        }
    }

    /**
     * Add a cell to the dependencies map
     *
     * @param curCell Cell to add
     */
    private void addToDependenciesMap(Cell curCell) {
        LinkedList<ReferenceToken> curCellDeps = curCell.getReferences();
        for (ReferenceToken tok : curCellDeps) {
            HashSet<Cell> refBy;
            if (dependenciesMap.containsKey(tok.hashCode())) {
                refBy = dependenciesMap.get(tok.hashCode());
            } else {
                dependenciesMap.put(tok.hashCode(), refBy = new HashSet<Cell>());
            }
            refBy.add(curCell);
        }
    }
}
