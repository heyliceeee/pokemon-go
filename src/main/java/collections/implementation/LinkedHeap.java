package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotComparableInstanceException;
import collections.interfaces.HeadADT;

/**
 * Classe implementa uma pilha ligada
 * @param <T>
 */
public class LinkedHeap<T> extends LinkedBinaryTree<T> implements HeadADT<T>
{
    /**
     * último node da pilha
     */
    public HeapNode<T> lastNode;

    /**
     * constructor
     */
    public LinkedHeap()
    {
        super();
    }

    @Override
    public void addElement(T obj) throws NotComparableInstanceException
    {
        HeapNode<T> node = new HeapNode<>(obj);

        if (root == null)
        {
            root = node;
        }
        else
        {
            HeapNode<T> next_parent = getNextParentAdd();

            if (next_parent.getLeft() == null)
            {
                next_parent.setLeft(node);
            }
            else
            {
                next_parent.setRight(node);
            }

            node.parent = next_parent;
        }

        lastNode = node;
        count++;

        if (count > 1)
        {
            heapifyAdd();
        }
    }

    @Override
    public T removeMin() throws EmptyCollectionException
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("Empty Heap");
        }

        T minElement = root.getElement();

        if (count == 1)
        {
            root = null;
            lastNode = null;
        }
        else
        {
            HeapNode<T> next_last = getNewLastNode();

            if (lastNode.getParent().getLeft() == lastNode)
            {
                lastNode.getParent().setLeft(null);
            }
            else
            {
                lastNode.getParent().setRight(null);
            }

            root.setElement(lastNode.getElement());
            lastNode = next_last;
            heapifyRemove();
        }
        count--;

        return minElement;
    }

    @Override
    public T findMin() throws EmptyCollectionException
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("A lista está vazia");
        }

        return root.getElement();
    }

    /**
     *
     * @return
     */
    private HeapNode<T> getNextParentAdd()
    {
        HeapNode<T> result = lastNode;

        //percorre a heap até ao último node
        while ((result != root) && (result.getParent().getLeft() != result))
        {
            result = result.getParent();
        }

        if (result != root)
        {
            if (result.getParent().getRight() == null)
            {
                result = result.getParent();
            }
            else
            {
                result = (HeapNode<T>) result.getParent().getRight();

                while (result.getLeft() != null)
                {
                    result = (HeapNode<T>) result.getLeft();
                }
            }
        }
        else
        {
            while (result.getLeft() != null)
            {
                result = (HeapNode<T>) result.getLeft();
            }
        }
        return result;
    }

    /**
     * Retorna o node que será o novo último node após uma remoção
     *
     * @return o node que será o novo último node após uma remoção
     */
    private HeapNode<T> getNewLastNode()
    {
        HeapNode<T> result = lastNode;

        while ((result != root) && (result.getParent().getLeft() == result))
        {
            result = result.parent;
        }

        if (result != root)
        {
            result = (HeapNode<T>) result.getParent().getLeft();
        }

        while (result.getRight() != null)
        {
            result = (HeapNode<T>) result.getRight();
        }

        return result;
    }

    /**
     * Reordena este heap após adicionar o elemento raiz
     */
    private void heapifyAdd()
    {
        T temp;
        HeapNode<T> next = this.lastNode;
        temp = next.getElement();

        while (next != root && ((Comparable) temp).compareTo(next.getParent().getElement()) < 0)
        {
            next.setElement(next.getParent().getElement());
            next = next.getParent();
        }

        next.setElement(temp);
    }

    /**
     * Reordena este heap após remover o elemento raiz.
     */
    private void heapifyRemove()
    {
        T temp;
        //variáveis temporárias para a raiz e filhos da árvore
        HeapNode<T> node = (HeapNode<T>) root;
        HeapNode<T> left = (HeapNode<T>) node.getLeft();
        HeapNode<T> right = (HeapNode<T>) node.getRight();
        HeapNode<T> next;

        if ((left == null) && (right == null))
        {
            next = null;
        }
        else if (left == null)
        {
            next = right;
        }
        else if (right == null)
        {
            next = left;
        }
        else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0)
        {
            next = left;
        }
        else
        {
            next = right;
        }

        temp = node.getElement();

        while ((next != null) && (((Comparable) next.getElement()).compareTo(temp) < 0))
        {
            node.setElement(next.getElement());
            node = next;
            left = (HeapNode<T>) node.getLeft();
            right = (HeapNode<T>) node.getRight();

            if ((left == null) && (right == null))
            {
                next = null;
            }
            else if (left == null)
            {
                next = right;
            }
            else if (right == null)
            {
                next = left;
            }
            else if (((Comparable) left.getElement()).compareTo(right.getElement()) < 0)
            {
                next = left;
            }
            else
            {
                next = right;
            }
        }
        node.setElement(temp);
    }
}
