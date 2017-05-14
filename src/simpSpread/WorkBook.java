package simpSpread;

import simpSpread.Cell.Cell;
import simpSpread.Cell.Provider;
import simpSpread.Exception.CannotRead;
import simpSpread.Exception.CircularDependencyException;
import simpSpread.Exception.InvalidValue;
import simpSpread.Exception.NoInputToEvaluate;

import java.util.*;

/**
 * Class to represent the workbook for the spreadsheet solver Separated out from the main spreadsheet class to  support
 * future implementation of multiple workbooks in a spreadsheet
 *
 * @author A. Student
 */
public class WorkBook {
	private Provider cellProvider;
	private simpSpread.Evaluator.WorkBook evaluator;

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

	public WorkBook(Provider cellProvider, simpSpread.Evaluator.WorkBook evaluator) {
		this.cellProvider = cellProvider;
		this.evaluator = evaluator;
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

		evaluator.evaluate(n*m, cellMatrix, dependenciesMap, topologicalList);
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
