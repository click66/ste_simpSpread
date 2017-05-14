package simpSpread.Operation;

import org.junit.Test;
import simpSpread.Operators;
import simpSpread.Token.OperatorToken;

import static org.junit.Assert.*;

/**
 * Tests for Operations factory
 *
 * @author C. Sirl
 */
public class FactoryTest
{
    @Test
    public void getAddOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("+"))) instanceof Add
        );
    }

    @Test
    public void getSubtractOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("-"))) instanceof Subtract
        );
    }

    @Test
    public void getMultiplyOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("*"))) instanceof Multiply
        );
    }

    @Test
    public void getDivideOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("/"))) instanceof Divide
        );
    }

    @Test
    public void getIncrementOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("++"))) instanceof Increment
        );
    }


    @Test
    public void getDecrementOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("--"))) instanceof Decrement
        );
    }


    @Test
    public void getSineOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("S"))) instanceof Sine
        );
    }


    @Test
    public void getCosineOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("C"))) instanceof Cosine
        );
    }


    @Test
    public void getTangentOperation() throws Exception
    {
        assertTrue(
            construct().getOperation(new OperatorToken(Operators.get("T"))) instanceof Tangent
        );
    }


    private Factory construct()
    {
        return new Factory();
    }
}