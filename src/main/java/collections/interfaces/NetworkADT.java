package collections.interfaces;

/**
 * NetworkADT define a interface para uma rede
 * @param <T>
 */
public interface NetworkADT<T> extends GraphADT<T>
{
    /**
     * Insere uma aresta entre dois vértices deste grafo
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight o peso
     */
    public void addEdge(T vertex1, T vertex2, double weight);

    /**
     * Retorna o peso do caminho mais curto nesta rede
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @return o peso do caminho mais curto nesta rede
     */
    public double shortestPathWeight(T vertex1, T vertex2);
}
