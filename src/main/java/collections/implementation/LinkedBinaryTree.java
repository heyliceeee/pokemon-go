package collections.implementation;

import collections.exceptions.ElementNotFoundException;
import collections.exceptions.EmptyCollectionException;
import collections.interfaces.BinaryTreeADT;

import java.util.Iterator;

/**
 * Classe da árvore binária ligada implementa uma árvore binaria
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{
    /**
     * raíz da árvore
     */
    protected BinaryTreeNode<T> root;

    /**
     * contador de número de nodes
     */
    protected int count;


    /**
     * cria uma árvore binária vazia
     */
    public LinkedBinaryTree()
    {
        count = 0;
        root = null;
    }

    /**
     * cria uma árvore binária com o elemento especificado como raiz
     * @param element o elemento que se tornará a raiz da nova árvore binária
     */
    public LinkedBinaryTree(T element)
    {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @Override
    public T getRoot() throws EmptyCollectionException
    {
        if (isEmpty())
        {
            throw new EmptyCollectionException("A lista está vazia");
        }

        return this.root.getElement();
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
    public boolean contains(T targetElement)
    {
        return (this.findAgain(targetElement, root) != null);
    }

    /**
     * Retorna uma referência ao elemento de destino especificado se for encontrado nesta árvore binária
     * @param targetElement o elemento que está sendo procurado nesta árvore
     * @param next o elemento a partir do qual começar a pesquisar
     * @return
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next)
    {
        if (next == null)
        {
            return null;
        }

        if (next.element.equals(targetElement))
        {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);

        if (temp == null)
        {
            temp = findAgain(targetElement, next.right);
        }

        return temp;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException
    {
        BinaryTreeNode<T> node = this.findAgain(targetElement, this.root);

        if(node == null)
        {
            throw new ElementNotFoundException("binary tree");
        }

        return node.element;
    }

    @Override
    public Iterator<T> iteratorInOrder()
    {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        this.inOrder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia recursiva em ordem
     * @param node o node a ser usado como raiz para esta travessia
     * @param tempList a lista temporária para uso nesta travessia
     */
    private void inOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList)
    {
        if (node != null)
        {
            this.inOrder(node.left, tempList);
            tempList.addToRear(node.element);
            this.inOrder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder()
    {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        this.preOrder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia recursiva de pré-ordem
     * @param node o node a ser usado como raiz para esta travessia
     * @param tempList a lista temporária para uso nesta travessia
     */
    private void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList)
    {
        if (node != null)
        {
            tempList.addToRear(node.element);
            this.preOrder(node.left, tempList);
            this.preOrder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder()
    {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        this.postOrder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia recursiva de pós-ordem
     * @param node o node a ser usado como raiz para esta travessia
     * @param tempList a lista temporária para uso nesta travessia
     */
    private void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList)
    {
        if (node != null)
        {
            this.postOrder(node.left, tempList);
            this.postOrder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<T>();
        this.levelOrder(this.root, tempList);

        return tempList.iterator();
    }

    /**
     * Executa uma travessia recursiva de ordem de nível
     * @param root o node a ser usado como raiz para esta travessia
     * @param results o resultado desta travessia
     */
    private void levelOrder(BinaryTreeNode<T> root, ArrayUnorderedList<T> results) throws EmptyCollectionException {
        LinkedQueue<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        BinaryTreeNode<T> node = null;
        nodes.enqueue(root);

        while (!nodes.isEmpty())
        {
            node = nodes.dequeue();

            if (node.element != null)
            {
                results.addToRear(node.element);
            }
            else
            {
                results.addToRear(null);
            }

            if (node.getLeft() != null)
            {
                nodes.enqueue(node.getLeft());
            }

            if (node.getRight() != null)
            {
                nodes.enqueue(node.getRight());
            }
        }
    }

    @Override
    public String toString()
    {
        String s = "Iterator LevelOrder:";
        Iterator<T> itr = null;

        try
        {
            itr = iteratorLevelOrder();
        }
        catch (EmptyCollectionException e)
        {
            throw new RuntimeException(e);
        }

        s += " | ";

        while (itr.hasNext())
        {
            s += itr.next() + " | ";
        }

        return s;
    }
}
