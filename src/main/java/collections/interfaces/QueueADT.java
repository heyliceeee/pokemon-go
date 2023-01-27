package collections.interfaces;

import collections.exceptions.EmptyCollectionException;

/**
 * Interface do queue
 * @param <T>
 */
public interface QueueADT<T>
{
    /**
     * Adiciona um elemento na cauda da queue
     * @param element o elemento que vai ser adicionado na cauda da queue
     */
    public void enqueue(T element);

    /** Remove e retorna o elemento da cabeça da queue
     * @return o elemento da cabeça da queue
     */
    public T dequeue() throws EmptyCollectionException;

    /** Retorna sem remover o elemento da cabeça da queue
     * @return o primeiro elemento da queue
     */
    public T first() throws EmptyCollectionException;

    /** Retorna true se a queue estiver vazia, false caso contrário
     * @return true se a queue estiver vazia
     */
    public boolean isEmpty();

    /** Retorna o número de elementos da queue
     * @return o número de elementos da queue
     */
    public int size();

    /** Retorna uma string desta queue
     * @return uma string desta queue
     */
    @Override
    public String toString();
}
