package collections.implementation;

import collections.interfaces.BinaryTreeADT;

/**
 * Classe representa um node em uma árvore binária com um filho esquerdo e direito
 * @param <T>
 */
public class BinaryTreeNode<T>
{
    /**
     * elemento do node
     */
    protected T element;

    /**
     * filhos
     */
    protected BinaryTreeNode<T> left, right;


    /**
     * criar uma novo node de árvore com os dados específicos
     * @param o o elemento que fará parte do novo node da árvore
     */
    public BinaryTreeNode(T o)
    {
        this.element = o;
        this.left = null;
        this.right = null;
    }

    /**
     * Retorna o elemento do node
     * @return o elemento do node
     */
    public T getElement()
    {
        return element;
    }

    /**
     * Define o elemento do node
     * @param element o elemento do node
     */
    public void setElement(T element)
    {
        this.element = element;
    }

    /**
     * Retorna o filho esquerdo do node
     * @return o filho esquerdo do node
     */
    public BinaryTreeNode<T> getLeft()
    {
        return left;
    }

    /**
     * Define o filho esquerdo do node
     * @param left o filho esquerdo do node
     */
    public void setLeft(BinaryTreeNode<T> left)
    {
        this.left = left;
    }

    /**
     * Retorna o filho direito do node
     * @return o filho direito do node
     */
    public BinaryTreeNode<T> getRight()
    {
        return right;
    }

    /**
     * Define o filho direito do node
     * @param right o filho direito do node
     */
    public void setRight(BinaryTreeNode<T> right)
    {
        this.right = right;
    }

    /**
     * Retorna o número de filhos não nulos deste node
     * @return o número de filhos não nulos deste node
     */
    public int numChildren()
    {
        int children = 0;

        if (left != null)
        {
            children = 1 + left.numChildren();
        }

        if (right != null)
        {
            children = children + 1 + right.numChildren();
        }

        return children;
    }
}
