package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotComparableInstanceException;

public class PriorityQueue<T> extends LinkedHeap<PriorityQueueNode<T>>
{
    /**
     * Cria uma priority queue vazia
     */
    public PriorityQueue()
    {
        super();
    }


    /**
     * Adiciona o elemento dado a esta priority queue
     * @param object o elemento a ser adicionado à priorityqueue
     * @param priority a prioridade do elemento a ser adicionado
     * @throws NotComparableInstanceException
     */
    public void addElement(T object, int priority) throws NotComparableInstanceException
    {
        PriorityQueueNode<T> node = new PriorityQueueNode<>(object, priority);

        super.addElement(node);
    }

    /**
     * Remove o próximo elemento de prioridade mais alta desta queue de prioridade e retorna uma referência a ele
     * @return uma referência ao próximo elemento de prioridade mais alta nesta queue
     * @throws EmptyCollectionException
     */
    public T removeNext() throws EmptyCollectionException
    {
        PriorityQueueNode<T> temp = super.removeMin();

        return temp.getElement();
    }
}
