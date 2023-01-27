package collections.interfaces;

import collections.exceptions.EmptyCollectionException;

public interface UnorderedListADT<T> extends ListADT<T>
{
    /**
     * Adiciona o elemento na frente da lista
     * @param element o elemento que vai ser adicionado na frente da lista
     */
    public void addToFront(T element);

    /**
     * Adiciona o elemento no fim da lista
     * @param element o elemento que vai ser adicionado no fim da lista
     */
    public void addToRear(T element);

    /**
     * Adiciona o elemento após um elemento especificado já na lista
     * @param element o elemento que vai ser adicionado na lista
     * @param target o elemento especificado na lista
     * @throws EmptyCollectionException
     */
    public void addAfter(T element, T target) throws EmptyCollectionException;
}
