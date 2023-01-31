package collections.exceptions;

public class EmptyCollectionException extends Exception
{
    public EmptyCollectionException(String m)
    {
        super("Error: "+m);
    }
}
