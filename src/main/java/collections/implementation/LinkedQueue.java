package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.interfaces.QueueADT;

/**
 * Classe que implementa uma LinkedQueue
 * @param <T>
 */
public class LinkedQueue<T> implements QueueADT<T>
{
    /**
     * tamanho da queue
     */
    private int count;

    /**
     * primeiro node
     */
    private LinkedNode<T> front;

    /**
     * último node
     */
    private LinkedNode<T> rear;


    /**
     * constructor
     */
    public LinkedQueue()
    {
        this.count = 0;
        this.front = null;
        this.rear = null;
    }

    @Override
    public void enqueue(T element)
    {
        //definir LinkedNode
        LinkedNode<T> newNode = new LinkedNode<T>(element);
        newNode.setElement(element);

        //verificar se a queue está vazia
        if(front == null)
        {
            rear = newNode;
            front = newNode;

        } else
        {
            rear.setNext(newNode);
            rear = newNode;
        }

        this.count++;
    }

    @Override
    public T dequeue() throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("a queue está vazia");
        }

        //definir linkedNode
        LinkedNode<T> temp = this.front;

        //avançar para o próximo node
        this.front = this.front.getNext();

        //se a queue ficar vazia
        if(front == null)
        {
            this.rear = null;
        }

        //node removido
        T item = temp.getElement();

        this.count--;

        return item;
    }

    @Override
    public T first() throws EmptyCollectionException
    {
        if(isEmpty())
        {
            throw new EmptyCollectionException("a queue está vazia");
        }

        //elemento da cabeça
        T result = front.getElement();

        return result;
    }

    @Override
    public boolean isEmpty()
    {
        return (count == 0);
    }

    @Override
    public int size()
    {
        return this.count;
    }

    @Override
    public String toString()
    {
        LinkedNode<T> current = this.front;
        String queue = "\n";

        while (current != null)
        {
            queue += current.getElement().toString() + "\n";
            current = current.getNext();
        }

        return queue;
    }
}
