package collections.implementation;

/**
 * Classe que implementa um node de uma árvore binária
 * @param <T>
 */
public class HeapNode<T> extends BinaryTreeNode<T>
{
    /**
     * pai
     */
    protected HeapNode<T> parent;

    /**
     * criar uma novo node de árvore com os dados específicos
     *
     * @param o o elemento que fará parte do novo node da árvore
     */
    public HeapNode(T o)
    {
        super(o);
        this.parent = null;
    }

    /**
     * Retorna o valor de pai
     * @return o valor de pai
     */
    public HeapNode<T> getParent()
    {
        return parent;
    }

    /**
     * Define o valor de pai
     * @param parent
     */
    public void setParent(HeapNode<T> parent)
    {
        this.parent = parent;
    }
}
