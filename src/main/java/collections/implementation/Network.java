package collections.implementation;

import collections.interfaces.NetworkADT;

/**
 * Classe que implementa o contrato de rede
 * @param <T>
 */
public class Network<T> extends Graph<T> implements NetworkADT<T>
{

    @Override
    public void addEdge(T vertex1, T vertex2, double weight)
    {
        if (weight < 0)
        {
            throw new IllegalArgumentException("Weight must be bigger or equal than 0 in this domain");
        }

        this.addEdge(super.getIndex(vertex1), super.getIndex(vertex2), weight);
    }

    /**
     * Adiciona o peso da aresta
     * @param index1 indice da primeira vertice
     * @param index2 indice da segunda vertice
     * @param weight peso da vértice
     */
    protected void addEdge(int index1, int index2, double weight)
    {
        if (!super.indexIsValid(index1))
        {
            throw new IllegalArgumentException("First vertex isn't valid");
        }

        if (!super.indexIsValid(index2))
        {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }

        super.adjMatrix[index1][index2] = weight;
        super.adjMatrix[index2][index1] = weight;
    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2)
    {
        int startVertex = getIndex(vertex1), endVertex = getIndex(vertex2), currentIndex;
        double[] shortestDistances = new double[super.numVertices]; //lista das distancias mais curtas de todos os vértices desde o inicio
        boolean[] visited = new boolean[super.numVertices]; //lista de vértices que já foram visitadas

        for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        shortestDistances[startVertex] = 0; //a distância do primeiro node de si mesmo é 0
        double tmpDistance, edgeDistance;

        for(int i=0; i < super.numVertices && !visited[endVertex]; i++)
        {
            currentIndex = getSmallDistanceNode(shortestDistances, visited); //get the index of the nearest node
            visited[currentIndex] = true;

            for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++)
            {
                edgeDistance = super.adjMatrix[currentIndex][vertexIndex]; //retorna a distância entre o node próximo e todos os outros nodes
                tmpDistance = shortestDistances[currentIndex] + edgeDistance; //calcular a distância

                if (edgeDistance > 0 && (tmpDistance < shortestDistances[vertexIndex]))
                {
                    shortestDistances[vertexIndex] = tmpDistance;
                }
            }
        }

        return shortestDistances[endVertex];
    }

    /**
     * Retorna o indice do node com pequena distância
     * @param shortestDistances lista atual de distâncias do ponto A
     * @param visited lista que contém as informações sobre um node visitado ou não
     * @return indice do node com pequena distância
     */
    protected int getSmallDistanceNode(double[] shortestDistances, boolean[] visited)
    {
        int index = -1;
        double shortestDistance = Double.MAX_VALUE;

        for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++)
        {
            if (!visited[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) //pega o node próximo
            {
                index = vertexIndex;
                shortestDistance = shortestDistances[vertexIndex];
            }
        }

        return index;
    }
}
