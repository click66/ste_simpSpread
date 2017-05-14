package simpSpread.Evaluator;

import simpSpread.Cell.Cell;
import simpSpread.Exception.CircularDependencyException;
import simpSpread.Exception.NoInputToEvaluate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * WorkBook class. Handles the evaluation of an inputted collection of cells
 *
 * @author C. Sirl
 */
public class WorkBook
{
    private simpSpread.Evaluator.Cell cellEvaluator;

    public WorkBook(simpSpread.Evaluator.Cell cellEvaluator)
    {
        this.cellEvaluator = cellEvaluator;
    }

    /**
     * Function to evaluate the workbook cell by cell in topological order
     *
     * @throws simpSpread.Exception.NoInputToEvaluate : if readInput() has not first been called
     * @throws CircularDependencyException : if workbook has cell which are circularly dependent on each other
     * @throws RuntimeException:            if parsing of token during evaluation fails
     */
    public void evaluate(
        int cellCount,
        Cell[][] cellMatrix,
        HashMap<Integer, HashSet<Cell>> dependenciesMap,
        LinkedList<Cell> topologicalList
    ) throws CircularDependencyException, NoInputToEvaluate, RuntimeException {
        while (topologicalList.size() > 0) {
            Cell curCell = topologicalList.removeFirst();
            cellEvaluator.evaluate(curCell, cellMatrix);
            cellCount--;
            resolveDependencies(dependenciesMap, topologicalList, curCell);
        }
        if (cellCount != 0) {
            throw new CircularDependencyException("CircularDependencyFound: Unable to solve the workbook");
        }
    }

    private void resolveDependencies(
        HashMap<Integer, HashSet<Cell>> dependenciesMap,
        LinkedList<Cell> topologicalList,
        Cell curCell
    ) {
        // get all the cells dependent on this cell
        if (dependenciesMap.containsKey(curCell.hashCode())) {
            HashSet<Cell> curCellDeps = dependenciesMap.get(curCell.hashCode());
            if (curCellDeps.size() > 0) { // if there are cells dependent on this one
                for (Cell depCell : curCellDeps) {
                    depCell.setUnresolvedRefs(depCell.getUnresolvedRefs() - 1);
                    if (depCell.getUnresolvedRefs() == 0) // if all references resolved then add to topological list
                        topologicalList.add(depCell);
                }
            }
        }
    }
}
