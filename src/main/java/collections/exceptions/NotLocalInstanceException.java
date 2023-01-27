package collections.exceptions;

import api.interfaces.ILocal;

/**
 * Lança exceção quando o elemento em questão deveria ser instânciado {@link ILocal local} mas não é
 */
public class NotLocalInstanceException extends Exception {

    /**
     * construtor sem mensagem.
     */
    public NotLocalInstanceException() {
        super();
    }

    /**
     * construtor com mensagem.
     *
     * @param s mensagem.
     */
    public NotLocalInstanceException(String s) {
        super(s);
    }
}
