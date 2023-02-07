package collections.exceptions;

<<<<<<< HEAD
<<<<<<< HEAD
/**
 * Throws exception when element in question must be instanced of {@link ILocal place} but isn't.
 */
public class NotLocalnstanceException extends Exception {

    /**
     * Constructor without message.
=======
=======
>>>>>>> 1827a764c995d9b60cbe30876dd7fe5553cdc96c
import api.interfaces.ILocal;

/**
 * Lança exceção quando o elemento em questão deveria ser instânciado {@link ILocal local} mas não é
 */
public class NotLocalInstanceException extends Exception {

    /**
     * construtor sem mensagem.
<<<<<<< HEAD
>>>>>>> development_alice_v2
=======
>>>>>>> 1827a764c995d9b60cbe30876dd7fe5553cdc96c
     */
    public NotLocalInstanceException() {
        super();
    }

    /**
<<<<<<< HEAD
<<<<<<< HEAD
     * Constructor without message.
     *
     * @param s message.
=======
     * construtor com mensagem.
     *
     * @param s mensagem.
>>>>>>> development_alice_v2
=======
     * construtor com mensagem.
     *
     * @param s mensagem.
>>>>>>> 1827a764c995d9b60cbe30876dd7fe5553cdc96c
     */
    public NotLocalInstanceException(String s) {
        super(s);
    }
}
