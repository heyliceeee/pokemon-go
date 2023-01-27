package collections.implementation;

/**
 * Node de uma linkedlist que contém: os dados do seu próximo, cria a sua referência e armazena os seus dados
 * @param <T>
 */
public class LinkedNode<T>
{
    private LinkedNode<T> next;
    private T element;

    public LinkedNode()
    {
        element = null;
        next = null;
    }

    public LinkedNode(T element)
    {
        this.element = element;
        next = null;
    }

    public LinkedNode<T> getNext()
    {
        return next;
    }

    public void setNext(LinkedNode<T> next)
    {
        this.next = next;
    }

    public T getElement()
    {
        return element;
    }

    public void setElement(T element)
    {
        this.element = element;
    }

    @Override
    public String toString()
    {
        return "{" + "element=" + element + '}';
    }
}
