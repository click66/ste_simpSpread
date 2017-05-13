package simpSpread;

import org.mockito.internal.matchers.Null;
import simpSpread.Cell.Cell;
import simpSpread.Cell.Provider;
import simpSpread.Exception.CannotRead;
import simpSpread.Exception.CircularDependencyException;
import simpSpread.Exception.InvalidValue;
import simpSpread.Exception.NoInputToEvaluate;
import simpSpread.Token.OperatorToken;
import simpSpread.Token.ReferenceToken;
import simpSpread.Token.Token;
import simpSpread.Token.ValueToken;

import java.util.*;

/**
 * Class to represent the workbook for the spreadsheet solver Separated out from the main spreadsheet class to  support
 * future implementation of multiple workbooks in a spreadsheet
 *
 * @author A. Student
 */
public class WorkBook {
	private Provider cellProvider;

	private LinkedList<Cell> topologicalList;
	private HashMap<Integer, HashSet<Cell>> dependenciesMap;
	private int n; // number of columns (width)
	private int m; // number of rows (height)
	private Cell[][] cellMatrix;
	private int unsolvedCells;
	private boolean circularDependent = false;

	public boolean isCircularDependent() {
		return circularDependent;
	}

	public void setCircularDependent(boolean circularDependent) {
		this.circularDependent = circularDependent;
	}

	public WorkBook(Provider cellProvider) {
		this.cellProvider = cellProvider;
	}

	public void readInput() throws CannotRead, InvalidValue, RuntimeException {
		n = cellProvider.getColumnCount();
		m = cellProvider.getRowCount();
		unsolvedCells = n * m;

		cellMatrix = cellProvider.getCellMatrix();
		topologicalList = cellProvider.getTopologicalList();
		dependenciesMap = cellProvider.getDependenciesMap();
	}

	/**
	 * Function to evaluate the workbook cell by cell in topological order
	 *
	 * @throws simpSpread.Exception.NoInputToEvaluate : if readInput() has not first been called
	 * @throws CircularDependencyException : if workbook has cell which are circularly dependent on each other
	 * @throws RuntimeException:            if parsing of token during evaluation fails
	 */
	public void evaluate() throws CircularDependencyException, NoInputToEvaluate, RuntimeException {
		if (topologicalList == null || cellMatrix == null || dependenciesMap == null) {
			throw new NoInputToEvaluate("No input has been read. Did you forget to call readInput() first?");
		}

		while (topologicalList.size() > 0) {
			Cell curCell = topologicalList.removeFirst();
			evaluate(curCell);
			unsolvedCells--;
			resolveDependencies(curCell);
		}
		if (unsolvedCells != 0) {
			setCircularDependent(true);
			throw new CircularDependencyException("CircularDependencyFound: Unable to solve the workbook");
		}
	}

	private void resolveDependencies(Cell curCell) {
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

	/**
	 * Function to evaluate a cell
	 *
	 * @param curCell the cell to be evaluated
	 * @return the evaluated value of the cell
	 * @throws RuntimeException:         if invalid token is found while evaluating
	 * @throws IllegalArgumentException: If the evaluation of operator is done on illegal argument like divide by 0
	 */
	private double evaluate(Cell curCell) throws RuntimeException {
		if (curCell.isEvaluated())
			return curCell.getEvaluatedValue();

		Stack<Double> RPNStack = new Stack<Double>();
		LinkedList<Token> curCellTokens = curCell.getTokenList();

		for (Token tok : curCellTokens) {
			if (tok.getClass().equals(ValueToken.class)) {
				RPNStack.push(((ValueToken) tok).getParsedValue());
			} else if (tok.getClass().equals(ReferenceToken.class)) {
				ReferenceToken refTok = (ReferenceToken) tok;
				Cell refCell = cellMatrix[refTok.getRefRow()][refTok.getRefCol()];
				RPNStack.push(evaluate(refCell));
			} else if (tok.getClass().equals(OperatorToken.class)) {
				OperatorToken opTok = (OperatorToken) tok;
				RPNStack = opTok.getParsedValue().apply(RPNStack);
			} else {
				throw new RuntimeException("Error: Invalid token: " + tok.toString());
			}
		}
		curCell.setEvaluatedValue(RPNStack.pop());
		curCell.setEvaluated(true);
		return curCell.getEvaluatedValue();
	}

	public String printWorkbook(boolean results) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (results) {
					if (cellMatrix[i][j].isEvaluated()) {
						String value = String.format("%.5f", cellMatrix[i][j].getEvaluatedValue());
						stringBuilder.append(value);
					} else {
						stringBuilder.append(Spreadsheet.NOT_EVALUATED);
						if (dependenciesMap.containsKey(cellMatrix[i][j].hashCode())) {
							stringBuilder.append(Spreadsheet.CYCLIC_DEPENDENCY);
						}
					}
				} else {
					stringBuilder.append(cellMatrix[i][j].getContents());
				}
				if (!(i == m - 1 && j == n - 1))
					stringBuilder.append(System.lineSeparator());
			}
		}
		return stringBuilder.toString();
	}

	public void prettyPrintWorkbook(boolean results) {
		String[][] matrix = new String[m + 1][n + 1];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0) {
					if (i == 0 && j == 0) {
						matrix[i][j] = " ";
					} else if (i == 0) {
						matrix[i][j] = String.valueOf(j);
					} else {
						matrix[i][j] = Utils.getRowName(i - 1);
					}
				} else {
					if (results) {
						if (cellMatrix[i - 1][j - 1].isEvaluated()) {
							matrix[i][j] = String.format("%.5f", cellMatrix[i - 1][j - 1].getEvaluatedValue());
						} else {
							matrix[i][j] = Spreadsheet.NOT_EVALUATED;
							if (dependenciesMap.containsKey(cellMatrix[i - 1][j - 1].hashCode())) {
								matrix[i][j] = matrix[i][j] + Spreadsheet.CYCLIC_DEPENDENCY;
							}
						}
					} else {
						matrix[i][j] = cellMatrix[i - 1][j - 1].getContents();
					}
				}
			}
		}
		if (results) {
			System.out.println("Results:");
		} else {
			System.out.println("Inputs:");
		}
		final PrettyPrinter printer = new PrettyPrinter(System.out);
		printer.print(matrix);
	}
}
