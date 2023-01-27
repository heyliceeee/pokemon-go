package collections.interfaces;

import java.util.Iterator;

/**
 * Define a interface para o grafo
 * @param <T>
 */
public interface GraphADT<T>
{
    /**
     * Adiciona um vértice a este grafo, associando o objeto ao vértice
     * @param vertex o vértice que vai ser adicionado a este grafo
     */
    public String addVertex(T vertex);

    /**
     * Remove um único vértice com o valor dado deste grafo
     * @param vertex o vértice que vai ser removido deste grafo
     */
    public String removeVertex(T vertex);

    /**
     * Insere uma aresta entre os dois vértices deste grafo
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     */
    public void addEdge(T vertex1, T vertex2);

    /**
     * Remove uma aresta entre os dois vértice deste grafo
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     */
    public void removeEdge(T vertex1, T vertex2);

    /**
     * Retorna um primeiro iterador de largura começando com o vértice fornecido
     * @param startVertex o vértice inicial
     * @return um primeiro iterador de largura começando com o vértice fornecido
     */
    public Iterator<T> iteratorBFS(T startVertex);

    /**
     * Retorna um primeiro iterador de profundidade começando com o vértice fornecido
     * @param startVertex o vértice inicial
     * @return um primeiro iterador de profundidade começando com o vértice fornecido
     */
    public Iterator<T> iteratorDFS(T startVertex);

    /**
     * Retorna um iterador que contém o caminho mais curto entre os dois vértices
     * @param startVertex o vértice inicial
     * @param targetVertex o vértice final
     * @return um iterador que contém o caminho mais curto entre os dois vértices
     */
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex);

    /**
     * Retorna true se este grafo estiver vazio, false caso contrário
     * @return true se este grafo estiver vazio
     */
    public boolean isEmpty();

    /**
     * Retorna true se este grafo estiver conectado, false caso contrário
     * @return true se este grafo estiver conectado
     */
    public boolean isConnected();

    /**
     * Retorna o número de vértices deste grafo
     * @return o número de vértices deste grafo
     */
    public int size();

    /**
     * Retorna uma string com a matriz adjacência
     * @return uma string com a matriz adjacência
     */
    public String toString();
}
