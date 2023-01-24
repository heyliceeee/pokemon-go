package collections.exceptions;

import api.interfaces.ILocal;

/**
 * Throws exception when element in question must be instanced of {@link ILocal place} but isn't.
 */
public class NotPlaceInstanceException extends Exception {

    /**
     * Constructor without message.
     */
    public NotPlaceInstanceException() {
        super();
    }

    /**
     * Constructor without message.
     *
     * @param s message.
     */
    public NotPlaceInstanceException(String s) {
        super(s);
    }
}
