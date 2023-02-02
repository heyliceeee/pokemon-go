package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.interfaces.UnorderedListADT;

import java.util.NoSuchElementException;

/**
 * Classe que implementa uma lista não ordenada usando nodes duplos
 * @param <T>
 */
public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T>
{

    @Override
    public void addToFront(T element)
    {
        if(super.count == 0)
        {
            super.head = new DoubleNode<>(null, null, element);
            super.tail = new DoubleNode<>(null, null, element);
        }
        else
        {
            DoubleNode<T> newNode = new DoubleNode<>(super.head, null, element);

            super.head.setPrev(newNode);
            super.head = newNode;
        }

        super.count++;
        super.modCount++;
    }

    @Override
    public void addToRear(T element)
    {
        if(super.count == 0)
        {
            super.head = new DoubleNode<>(null, null, element);
            super.tail = new DoubleNode<>(null, null, element);
        }
        else
        {
            DoubleNode<T> newNode = new DoubleNode<>(null, super.tail, element);

            super.tail.setNext(newNode);
            super.tail = newNode;
        }

        super.count++;
        super.modCount++;
    }

    @Override
    public void addAfter(T element, T target) throws EmptyCollectionException
    {
        DoubleNode<T> afterNode = super.find(target);

        if(afterNode != null)
        {
            if(afterNode.equals(super.tail))
            {
                this.addToRear(element);
            }
            else
            {
                DoubleNode<T> newNode = new DoubleNode<>(afterNode.getNext(), afterNode, element);

                afterNode.setNext(newNode);
                newNode.getNext().setPrev(newNode);

                super.count++;
                super.modCount++;
            }
        }
        else
        {
            throw new NoSuchElementException("Target element not found");
        }
    }
}
