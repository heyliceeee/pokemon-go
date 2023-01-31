package collections.exceptions;

public class NullException extends Exception
{
    public NullException(String m) {
        super("ERROR: " + m);
    }
}
