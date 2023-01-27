package collections.implementation;

import collections.exceptions.NullException;
import collections.interfaces.StackADT;

import java.util.EmptyStackException;

/**
 * Classe que implementa uma linkedStack
 * @param <T> tipo a ser guardado
 */
public class LinkedStack<T> implements StackADT<T>
{
    /**
     * contador do tamanho do linkedStack
     */
    int count;

    /**
     * primeiro node
     */
    LinkedNode<T> top;


    /**
     * constructor
     */
    public LinkedStack()
    {
        this.count = 0;
        this.top = null;
    }


    @Override
    public void push(T element)
    {
        //definir linkednode
        LinkedNode<T> newNode = new LinkedNode<>(element);
        newNode.setElement(element);

        //se a stack está vazia
        if(this.top == null)
        {
            this.top = newNode;

        } else //se a stack não estiver vazia
        {
            newNode.setNext(top);
            top = newNode;
        }

        this.count++;
    }

    @Override
    public T pop() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        T result = top.getElement();
        top = top.getNext();
        count--;

        return result;
    }

    @Override
    public T peek() throws EmptyStackException
    {
        if(isEmpty())
        {
            throw new EmptyStackException();
        }

        LinkedNode<T> temp = this.top;

        return temp.getElement();
    }

    @Override
    public boolean isEmpty()
    {
        return count == 0;
    }

    @Override
    public int size()
    {
        return count;
    }


    /**
     * Retorna uma string da stack
     * @return uma string da stack
     */
    public String toString()
    {
        LinkedNode<T> current = top;
        String linkedStack = "\n";

        while (current != null)
        {
            linkedStack += current.getElement().toString() + "\n";
            current = current.getNext();
        }

        return linkedStack;
    }
}
