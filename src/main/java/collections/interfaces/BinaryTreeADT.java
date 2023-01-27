package collections.interfaces;

import collections.exceptions.ElementNotFoundException;
import collections.exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * Interface da árvore binária
 * @param <T>
 */
public interface BinaryTreeADT<T>
{
    /**
     * Retorna uma referência ao elemento raíz
     * @return uma referência ao elemento raíz
     * @throws EmptyCollectionException
     */
    public T getRoot() throws EmptyCollectionException;

    /**
     * Retorna true se esta árvore binária estiver vazia e false caso contrário
     * @return true se esta árvore binária estiver vazia
     */
    public boolean isEmpty();

    /**
     * Retorna o número de elementos nesta árvore binária
     * @return o número de elementos nesta árvore binária
     */
    public int size();

    /**
     * Retorna true se a árvore binária contiver um elemento que corresponda ao elemento especificado e false caso contrário
     * @param targetElement o elemento que está sendo procurado na árvore
     * @return true se a árvore binária contiver um elemento que corresponda ao elemento especificado e false caso contrário
     */
    public boolean contains (T targetElement);

    /**
     * Retorna uma referência ao elemento de destino especificado se for encontrado nesta árvore binária. Lança uma NoSuchElementException se o elemento de destino especificado não for encontrado na árvore binária
     * @param targetElement o elemento que está sendo procurado nesta árvore
     * @return uma referência ao alvo especificado
     * @throws ElementNotFoundException se ocorrer uma exceção de elemento não encontrado
     */
    public T find (T targetElement) throws ElementNotFoundException;

    /**
     * Retorna uma string da árvore binária
     * @return uma string da árvore binária
     */
    public String toString();

    /**
     * Executa uma travessia inorder nesta árvore binária chamando um método inorder recursivo e sobrecarregado que começa com a raiz
     *
     * @return um iterador sobre os elementos desta árvore binária
     */
    public Iterator<T> iteratorInOrder();

    /**
     * Executa uma travessia de pré-ordem nesta árvore binária chamando um método de pré-ordem recursivo e sobrecarregado que começa com a raiz
     *
     * @return um iterador sobre os elementos desta árvore binária
     */
    public Iterator<T> iteratorPreOrder();

    /**
     * Executa uma travessia de pós-ordem nesta árvore binária chamando um método de pós-ordem recursivo e sobrecarregado que começa com a raiz
     *
     * @return um iterador sobre os elementos desta árvore binária
     */
    public Iterator<T> iteratorPostOrder();

    /**
     * Executa uma travessia de ordem de nível na árvore binária, usando uma queue
     *
     * @return um iterador sobre os elementos desta árvore binária
     */
    public Iterator<T> iteratorLevelOrder() throws EmptyCollectionException;
}
