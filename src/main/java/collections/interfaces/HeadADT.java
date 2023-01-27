package collections.interfaces;

import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotComparableInstanceException;

/**
 * Interface da pilha
 * @param <T>
 */
public interface HeadADT<T> extends BinaryTreeADT<T>
{
    /**
     * Adiciona o objeto especificado a esta pilha
     * @param o o elemento a ser adicionado a esta cabeça
     */
    void addElement(T o) throws NotComparableInstanceException;

    /**
     * Remove o elemento com o menor valor deste heap
     *
     * @return o elemento com o menor valor deste heap
     */
    T removeMin() throws EmptyCollectionException;

    /**
     * Retorna uma referência ao elemento com o menor valor neste heap
     *
     * @return uma referência ao elemento com o menor valor neste heap
     */
    T findMin() throws EmptyCollectionException;
}
