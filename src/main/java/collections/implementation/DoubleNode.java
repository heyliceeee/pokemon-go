package collections.implementation;

/**
 * Classe que representa um node duplo
 * @param <T>
 */
public class DoubleNode <T>
{
    /**
     * dados do elemento
     */
    private T element;

    /**
     * node anterior
     */
    private DoubleNode<T> prev;

    /**
     * node seguinte
     */
    private DoubleNode<T> next;


    public DoubleNode(DoubleNode<T> next, DoubleNode<T> prev, T element)
    {
        this.next = next;
        this.element = element;
        this.prev = prev;
    }

    /**
     * constructor
     * @param element
     */
    public DoubleNode(T element)
    {
        this.next = null;
        this.prev = null;
        this.element = element;
    }


    /**
     * Retorna o node seguinte
     * @return o node seguinte
     */
    public DoubleNode<T> getNext()
    {
        return this.next;
    }

    /**
     * Define o valor do node seguinte
     * @param next
     */
    public void setNext(DoubleNode<T> next)
    {
        this.next = next;
    }

    /**
     * Retorna os dados do elemento
     * @return os dados do elemento
     */
    public T getElement()
    {
        return this.element;
    }

    /**
     * Define o valor do elemento
     * @param elem
     */
    public void setElement(T elem)
    {
        this.element = elem;
    }

    /**
     * Retorna o node anterior
     * @return o node anterior
     */
    public DoubleNode<T> getPrev()
    {
        return this.prev;
    }

    /**
     * Define o valor do node anterior
     * @param prev
     */
    public void setPrev(DoubleNode<T> prev)
    {
        this.prev = prev;
    }

    @Override
    public String toString()
    {
        return "DoubleNode{" + "element=" + element + '}';
    }
}
