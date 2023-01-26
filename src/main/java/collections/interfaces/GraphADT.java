package collections.interfaces;

import collections.exceptions.EmptyCollectionException;

import java.util.Iterator;

/**
 * GraphADT define a interface para uma estrutura de dados de grafo
 * @param <T>
 */
public interface GraphADT<T>
{
    /**
     * Adiciona um vértice a este grafo, associando o objeto ao vértice
     * @param vertex o vértice que vai ser adicionado a este grafo
     * @return true se o vértice foi inserido, false caso contrário
     */
    public boolean addVertex(T vertex);

    /**
     * Remove um único vértice com o valor fornecido deste grafo
     * @param vertex o vértice que vai ser removido deste grafo
     */
    public void removeVertex(T vertex);

    /**
     * Insere uma aresta entre dois vértices deste grafo
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     */
    public void addEdge(T vertex1, T vertex2);

    /**
     * Remove uma aresta entre dois vértices deste grafo
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     */
    public void removeEdge(T vertex1, T vertex2);

    /**
     * Retorna o primeiro iterador de largura começando com o vértice fornecido
     * @param startVertex o vértice inicial
     * @return o primeiro iterador de largura começando no vértice fornecido
     */
    public Iterator<T> iteratorBFS(T startVertex);

    /**
     * Retorna o primeiro iterador de profundidade começando com o vértice fornecido
     * @param startVertex o vértice inicial
     * @return o primeiro iterador de profundidade começando no vértice fornecido
     */
    public Iterator<T> iteratorDFS(T startVertex);

    /**
     * Retorna um iterador que contém o caminho mais curto entre os dois vértices
     * @param startVertex o vértice inicial
     * @param targetVertex o vértice final
     * @return um iterador que contém o caminho mais curto entre os dois vértices
     */
    public Iterator iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Retorna true se este grafo está vazio, false caso contrário
     * @return true se este grafo está vazio
     */
    public boolean isEmpty();

    /**
     * Retorna true se este grafo está conectado, false caso contrário
     * @return true se este grafo está conectado
     * @throws EmptyCollectionException se o grafo está vazio
     */
    public boolean isConnected() throws EmptyCollectionException;

    /**
     * Retorna o número de vértices deste grafo
     * @return o número de vértices deste grafo
     */
    public int size();

    /**
     * Retorna em string da matriz de adjacência
     * @return em string da matriz de adjacência
     */
    public String toString();
}
