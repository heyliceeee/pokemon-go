package collections.exceptions;

/**
 * Lança exceção quando o elemento em questão deve ser instanciado de {@link api.interfaces.ILocal local}, mas não é.
 */
public class NotLocalInstanceException extends Exception
{
    /**
     * Construtor sem mensagem
     */
    public NotLocalInstanceException() {
        super();
    }

    /**
     * Constructor sem mensagem
     * @param s mensagem
     */
    public NotLocalInstanceException(String s)
    {
        super(s);
    }
}
