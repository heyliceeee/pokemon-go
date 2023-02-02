package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.interfaces.ListADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe que implementa uma lista usando nodes duplos
 * @param <T>
 */
public abstract class DoubleLinkedList<T> implements ListADT<T>
{
    /**
     * primeiro node
     */
    protected DoubleNode<T> head;

    /**
     * último node
     */
    protected DoubleNode<T> tail;

    /**
     * contagem do tamanho da lista
     */
    protected int count;

    /**
     * contador irá incrementar quando uma operação de adição ou eliminação ocorra
     */
    protected int modCount;


    /**
     * constructor
     */
    public void DoublyLinkedList()
    {
        this.head = null;
        this.tail = null;
        this.count = 0;
        this.modCount = 0;
    }


    @Override
    public T removeFirst() throws EmptyCollectionException
    {
        if (this.isEmpty())
        {
            throw new NoSuchElementException("List is empty");
        }

        DoubleNode<T> current = head;

        head = head.getNext();
        head.setPrev(null);

        this.count--;
        this.modCount++;

        return current.getElement();
    }

    @Override
    public T removeLast() throws NoSuchElementException
    {
        if (this.isEmpty())
        {
            throw new NoSuchElementException("List is empty");
        }

        DoubleNode<T> current = tail;

        tail = tail.getPrev();
        tail.setNext(null);

        this.count--;
        this.modCount++;

        return current.getElement();
    }

    @Override
    public T remove(T element) throws EmptyCollectionException
    {
        if (this.isEmpty())
        {
            throw new EmptyCollectionException("List is empty");
        }

        boolean found = false;
        DoubleNode<T> prev = null;
        DoubleNode<T> current = head;

        while (current != null && !found)
        {
            if(element.equals(current.getElement()))
            {
                found = true;
            }
            else
            {
                prev = current;
                current = current.getNext();
            }
        }

        if (!found)
        {
            throw new EmptyCollectionException("List is empty");
        }

        if(size() == 1)
        {
            head = null;
            tail = null;
        }
        else if(current.equals(head))
        {
            head = current.getNext();
        }
        else if(current.equals(tail))
        {
            tail = prev;
            tail.setNext(null);
        }
        else
        {
            prev.setNext(current.getNext());
        }

        this.count--;
        this.modCount++;

        return current.getElement();
    }


    protected DoubleNode<T> find(T element)
    {
        boolean found = false;
        DoubleNode<T> current = this.head;

        if (!this.isEmpty())
        {
            while (!found && current != null)
            {
                if (current.getElement().equals(element))
                {
                    found = true;
                }
                else
                {
                    current = current.getNext();
                }
            }
        }

        return found ? current : null;
    }

    @Override
    public T first() throws EmptyCollectionException
    {
        if (this.isEmpty())
        {
            throw new EmptyCollectionException("List is empty");
        }

        T result = head.getElement();

        return result;
    }

    @Override
    public T last() throws EmptyCollectionException
    {
        if (this.isEmpty())
        {
            throw new EmptyCollectionException("List is empty");
        }

        T result = tail.getElement();

        return result;
    }

    @Override
    public boolean contains(T target)
    {
        T data = head.getElement();
        boolean found = false;
        int i=0;

        while (i < this.count && !found)
        {
            if(target.equals(data))
            {
                found = true;
            }
            i++;
        }

        return found;
    }

    @Override
    public boolean isEmpty()
    {
        return (this.count == 0);
    }

    @Override
    public int size()
    {
        return this.count;
    }

    @Override
    public Iterator<T> iterator()
    {
        return new BasicIterator<>(this);
    }

    public class BasicIterator<T> implements Iterator<T>
    {
        /**
         * node atual
         */
        private DoubleNode<T> current;


        /**
         * constructor
         * @param list
         */
        public BasicIterator(DoubleLinkedList<T> list)
        {
            current = list.head;
        }

        @Override
        public boolean hasNext()
        {
            return (this.current != null);
        }

        @Override
        public T next()
        {
            T data = current.getElement();
            current = current.getNext();

            return data;
        }
    }

    @Override
    public String toString()
    {
        DoubleNode<T> current = this.head;
        String s = "DoubleLinkedList:\n";

        while(current != null)
        {
            s += current.getElement().toString() + "\n";
            current = current.getNext();
        }

        return s;
    }
}
