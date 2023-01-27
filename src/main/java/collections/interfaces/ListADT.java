package collections.interfaces;

import collections.exceptions.EmptyCollectionException;
import java.util.Iterator;

/**
 * Interface de uma lista
 * @param <T>
 */
public interface ListADT<T> extends Iterable<T>
{
    /** Remove e retorna o primeiro elemento da lista
     * @return o primeiro elemento da lista
     * @throws EmptyCollectionException
     */
    public T removeFirst() throws EmptyCollectionException;

    /** Remove e retorna o último elemento da lista
     * @return o último elemento da lista
     * @throws EmptyCollectionException
     */
    public T removeLast() throws EmptyCollectionException;

    /** Remove e retorna o específico elemento da lista
     * @param element o elemento que vai ser removido da lista
     * @return específico elemento da lista
     * @throws EmptyCollectionException
     */
    public T remove(T element) throws EmptyCollectionException;

    /** Retorna uma referência do primeiro elemento da lista
     * @return uma referência do primeiro elemento da lista
     * @throws EmptyCollectionException
     */
    public T first() throws EmptyCollectionException;

    /** Retorna uma referência do último elemento da lista.
     * @return uma referência do último elemento da lista
     * @throws EmptyCollectionException
     */
    public T last() throws EmptyCollectionException;

    /** Retorna true se a lista conter o elemento específico
     * @param target é o elemento está sendo procurado na lista
     * @return true se a lista conter o elemento específico
     */
    public boolean contains(T target);

    /** Retorna true se a lista não conter elementos
     * @return true se a lista não conter elementos
     */
    public boolean isEmpty();

    /** Retorna o número de elementos da lista
     * @return o número de elementos da lista
     */
    public int size();

    /** Retorna um iterador para os elementos da lista
     * @return  um iterador para os elementos da lista
     */
    public Iterator<T> iterator();

    /** Retorna uma string da lista
     * @return uma string da lista
     */
    @Override
    public String toString();
}
