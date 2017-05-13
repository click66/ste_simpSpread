package simpSpread;

import simpSpread.Exception.CannotRead;

import java.util.Scanner;

/**
 * Adapter for input scanner
 * Takes a Scanner input and populates a workbook
 *
 * @author C. Sirl
 */
public class InputScanner
{
    private Scanner scanner;

    public InputScanner(Scanner scanner)
    {
        this.scanner = scanner;
    }

    public int nextInt() throws CannotRead
    {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new CannotRead("Unable to read integer value");
        }
    }

    public String nextLine() throws CannotRead
    {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            throw new CannotRead("Unable to capture input");
        }
    }

    public void close()
    {
        this.scanner.close();
    }
}
