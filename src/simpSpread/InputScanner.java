package simpSpread;

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

    public int nextInt()
    {
        return scanner.nextInt();
    }

    public String nextLine()
    {
        return scanner.nextLine();
    }

    public void close()
    {
        this.scanner.close();
    }
}
