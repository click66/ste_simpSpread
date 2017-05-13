package simpSpread.Exception;

import java.io.FileNotFoundException;

/**
 * Exception class for invalid arguments
 *
 * @author C. Sirl
 */
public class FileNotFound extends FileNotFoundException
{
    public FileNotFound() {
    }

    public FileNotFound(String message) {
        super(message);
    }
}
