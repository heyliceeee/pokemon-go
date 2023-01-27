package collections.exceptions;

/**
 * Throws exception when element in question must be instanced of {@link ILocal place} but isn't.
 */
public class NotLocalnstanceException extends Exception {

    /**
     * Constructor without message.
     */
    public NotLocalInstanceException() {
        super();
    }

    /**
     * Constructor without message.
     *
     * @param s message.
     */
    public NotLocalInstanceException(String s) {
        super(s);
    }
}
