package collections.exceptions;

/**
 * Exceção que é lançada quando uma coleção está vazia
 */
public class EmptyCollectionException extends Exception {

    public EmptyCollectionException() {
        super();
    }

    public EmptyCollectionException(String s) {
        super(s);
    }

}
