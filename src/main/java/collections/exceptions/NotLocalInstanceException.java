package collections.exceptions;

<<<<<<< HEAD
/**
 * Throws exception when element in question must be instanced of {@link ILocal place} but isn't.
 */
public class NotLocalnstanceException extends Exception {

    /**
     * Constructor without message.
=======
import api.interfaces.ILocal;

/**
 * Lança exceção quando o elemento em questão deveria ser instânciado {@link ILocal local} mas não é
 */
public class NotLocalInstanceException extends Exception {

    /**
     * construtor sem mensagem.
>>>>>>> development_alice_v2
     */
    public NotLocalInstanceException() {
        super();
    }

    /**
<<<<<<< HEAD
     * Constructor without message.
     *
     * @param s message.
=======
     * construtor com mensagem.
     *
     * @param s mensagem.
>>>>>>> development_alice_v2
     */
    public NotLocalInstanceException(String s) {
        super(s);
    }
}
