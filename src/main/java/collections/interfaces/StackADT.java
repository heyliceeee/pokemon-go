package collections.interfaces;

import collections.exceptions.NullException;

import java.util.EmptyStackException;

/**
 * Interface da stack
 * @param <T>
 */
public interface StackADT<T>
{
    /**
     * Adiciona um elemento ao inicio da stack
     * @param element o elemento que vai ser adicionado á stack
     */
    void push(T element);

    /**
     * Remove e retorna o primeiro elemento da stack
     * @return elemento T removido do inicio da stack
     * @throws NullException se a stack estiver vazia
     */
    T pop() throws EmptyStackException;

    /**
     * Retorna sem remover o primeiro elemento da stack
     * @return o primeiro elemento
     * @throws NullException se a stack estiver vazia
     */
    T peek() throws EmptyStackException;

    /**
     * Retorna true se a stack não conter elementos, false caso contrário
     * @return true se a stack não conter elementos
     */
    boolean isEmpty();

    /**
     * Retorna o número de elementos na stack
     * @return o número de elementos na stack
     */
    int size();
}
