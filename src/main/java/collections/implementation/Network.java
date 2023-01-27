package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NotComparableInstanceException;
import collections.exceptions.UnknownPathException;
import collections.interfaces.NetworkADT;
import collections.interfaces.UnorderedListADT;

/**
 * Classe que implementa o contrato de rede
 * @param <T>
 */
public class Network<T> extends Graph<T> implements NetworkADT<T>
{
    /**
     * capacidade default da rede
     */
    protected final int DEFAULT_CAPACITY = 10;

    /**
     * matriz adjacência
     */
    private double[][] adjMatrix;


    /**
     * constructor
     */
    public Network()
    {
        super();
        this.adjMatrix = new double[this.DEFAULT_CAPACITY][this.DEFAULT_CAPACITY];
    }

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) throws EmptyCollectionException
    {
        if (weight < 0.0D)
        {
            throw new EmptyCollectionException("The weight cannot be under the default.");
        }
        else
        {
            super.addEdge(vertex1, vertex2);
            this.setEdgeWeight(vertex1, vertex2, weight);
        }
    }

    /**
     * Definir o peso da aresta
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight o peso
     */
    private void setEdgeWeight(T vertex1, T vertex2, double weight)
    {
        if (weight < 0.0D)
        {
            throw new IllegalArgumentException("The weight cannot be under the default.");
        }

        int first = this.getIndex(vertex1);
        int second = this.getIndex(vertex2);

        if (vertex2.equals("exterior") || vertex1.equals("exterior") || vertex2.equals("entrada") || vertex1.equals("entrada"))
        {
            this.adjMatrix[first][second] = 0;
            this.adjMatrix[second][first] = 0;
        }
        else
        {
            this.adjMatrix[first][second] = weight;
        }
    }

    @Override
    public ArrayUnorderedList<T> shortestPathWeight(T vertex1, T vertex2) throws EmptyCollectionException, UnknownPathException, NotComparableInstanceException {
        PriorityQueue<Pair<T>> priorityQueue = new PriorityQueue<>();
        UnorderedListADT<T> verticesFromPossiblePath = new ArrayUnorderedList<>();
        ArrayUnorderedList<T> result = new ArrayUnorderedList<>();
        Pair<T> startPair = new Pair<>(null, vertex1, 0.0);

        priorityQueue.addElement(startPair, (int) startPair.cost);

        while (!priorityQueue.isEmpty())
        {
            Pair<T> pair = priorityQueue.removeNext();
            T vertex = pair.vertex;
            double minCost = pair.cost;

            if (vertex.equals(vertex2))
            {
                Pair<T> finalPair = pair;

                while (finalPair != null)
                {
                    result.addToFront(finalPair.vertex);
                    finalPair = finalPair.previous;
                }

                return result;
            }

            verticesFromPossiblePath.addToRear(vertex);

            for (int i = 0; i < numVertices; i++)
            {
                if (super.adjMatrix[getIndex(vertex)][i] && !verticesFromPossiblePath.contains(vertices[i]))
                {
                    double minCostToVertex = minCost + adjMatrix[getIndex(vertex)][i];
                    Pair<T> tmpPair = new Pair<>(pair, vertices[i], minCostToVertex);
                    priorityQueue.addElement(tmpPair, (int) tmpPair.cost);
                }
            }
        }

        throw new UnknownPathException("Path doesn't exist");
    }
}
