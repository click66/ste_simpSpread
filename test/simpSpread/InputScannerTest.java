package simpSpread;

import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import simpSpread.Exception.CannotRead;
import simpSpread.Exception.Exception;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Integration tests for InputScanner adapter class
 *
 * @author C. Sirl
 */
public class InputScannerTest
{
    @Test
    public void nextInt() throws Exception
    {
        setSystemInput("1");

        InputScanner inputScanner = new InputScanner(new Scanner(System.in));

        assertEquals(1, inputScanner.nextInt());
    }

    @Test(expected=CannotRead.class)
    public void nextIntInvalid() throws Exception
    {
        setSystemInput("Testing is awesome");

        InputScanner inputScanner = new InputScanner(new Scanner(System.in));

        inputScanner.nextInt();
    }

    @Test
    public void nextLine() throws Exception
    {
        String testInput = "Dr. Rob Collins is a legend";

        setSystemInput(testInput);

        InputScanner inputScanner = new InputScanner(new Scanner(System.in));

        assertEquals(testInput, inputScanner.nextLine());
    }

    @Test(expected=CannotRead.class)
    public void close() throws Exception
    {
        setSystemInput("If you are reading this, something has gone wrong.");

        InputScanner inputScanner = new InputScanner(new Scanner(System.in));

        inputScanner.close();

        System.out.println(inputScanner.nextLine());
    }

    /**
     * Set up the system input to test values
     *
     * @param input "Mocked" input
     */
    private void setSystemInput(String input)
    {
        String data = input + "\r\n";
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
}
