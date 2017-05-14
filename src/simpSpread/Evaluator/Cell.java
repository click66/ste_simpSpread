package simpSpread.Evaluator;

import simpSpread.Exception.CircularDependencyException;
import simpSpread.Exception.NoInputToEvaluate;
import simpSpread.Operation.Factory;
import simpSpread.Token.OperatorToken;
import simpSpread.Token.ReferenceToken;
import simpSpread.Token.Token;
import simpSpread.Token.ValueToken;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Cell evaluator class. Handles the evaluation of a single cell
 *
 * @author C. Sirl
 */
public class Cell
{
    private Factory operationFactory;

    public Cell(Factory operationFactory)
    {
        this.operationFactory = operationFactory;
    }

    /**
     * Function to evaluate a cell
     *
     * @param curCell the cell to be evaluated
     * @param cellMatrix Cell matrix
     * @return the evaluated value of the cell
     * @throws RuntimeException:         if invalid token is found while evaluating
     * @throws IllegalArgumentException: If the evaluation of operator is done on illegal argument like divide by 0
     */
    public double evaluate(simpSpread.Cell.Cell curCell, simpSpread.Cell.Cell[][] cellMatrix) throws RuntimeException {
        if (curCell.isEvaluated())
            return curCell.getEvaluatedValue();

        Stack<Double> RPNStack = new Stack<Double>();
        LinkedList<Token> curCellTokens = curCell.getTokenList();

        for (Token tok : curCellTokens) {
            if (tok.getClass().equals(ValueToken.class)) {
                RPNStack.push(((ValueToken) tok).getParsedValue());
            } else if (tok.getClass().equals(ReferenceToken.class)) {
                ReferenceToken refTok = (ReferenceToken) tok;
                simpSpread.Cell.Cell refCell = cellMatrix[refTok.getRefRow()][refTok.getRefCol()];
                RPNStack.push(evaluate(refCell, cellMatrix));
            } else if (tok.getClass().equals(OperatorToken.class)) {
                OperatorToken opTok = (OperatorToken) tok;
                RPNStack = operationFactory.getOperation(opTok).apply(RPNStack);
            } else {
                throw new RuntimeException("Error: Invalid token: " + tok.toString());
            }
        }
        curCell.setEvaluatedValue(RPNStack.pop());
        curCell.setEvaluated(true);
        return curCell.getEvaluatedValue();
    }
}
