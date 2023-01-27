package collections.interfaces;

import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotComparableInstanceException;
import collections.exceptions.UnknownPathException;
import collections.implementation.ArrayUnorderedList;

/**
 * Define a interface da rede
 * @param <T>
 */
public interface NetworkADT<T> extends GraphADT<T>
{
    /**
     * Insere uma aresta entre dois vértices deste grafo
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight o peso
     * @throws EmptyCollectionException
     */
    public void addEdge (T vertex1, T vertex2, double weight) throws EmptyCollectionException;

    /**
     * Retorna o peso do caminho mais curto desta rede
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @return o peso do caminho mais curto desta rede
     * @throws EmptyCollectionException
     * @throws UnknownPathException
     */
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException, NotComparableInstanceException;
}
