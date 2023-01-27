package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.exceptions.NullException;
import collections.interfaces.GraphADT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @param <T>
 */
public class Graph<T> implements GraphADT<T>
{
    /**
     * capacidade inicial default
     */
    protected final int DEFAULT_CAPACITY = 10;

    /**
     * número de vértices no grafo
     */
    protected int numVertices;

    /**
     * matriz adjacente
     */
    protected boolean[][] adjMatrix;

    /**
     * valores dos vértices
     */
    protected T[] vertices;


    @SuppressWarnings("unchecked")
    public Graph()
    {
        this.numVertices = 0;
        this.adjMatrix = new boolean[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[])(new Object[DEFAULT_CAPACITY]);
    }


    @Override
    public String addVertex(T vertex)
    {
        if (vertex == null)
        {
            throw new IllegalArgumentException("Null vertex");
        }

        if (numVertices == vertices.length)
        {
            expandCapacity();
        }

        vertices[numVertices] = vertex;
        for (int i = 0; i <= numVertices; i++)
        {
            adjMatrix[numVertices][i] = false;
            adjMatrix[i][numVertices] = false;
        }
        numVertices++;

        return "Successful";
    }

    /**
     * Expande a capacidade o dobro do tamanho da matriz de adjacência e do array de vértices
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity()
    {
        T[] largerVertices = (T[])(new Object[vertices.length * 2]);
        boolean[][] largerAdjMatrix = new boolean[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++)
        {
            for (int j = 0; j < numVertices; j++)
            {
                largerAdjMatrix[i][j] = adjMatrix[i][j];
            }
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        adjMatrix = largerAdjMatrix;
    }

    @Override
    public String removeVertex(T vertex)
    {
        if (vertex == null)
        {
            throw new IllegalArgumentException("Null vertex");
        }

        for (int i = 0; i < numVertices; i++)
        {
            if (vertex.equals(vertices[i]))
            {
                removeVertex(i);
                return "Successful";
            }
        }

        return "Successful";
    }

    /**
     * Remove um único vértice com o valor dado do grafo
     * @param index
     */
    public void removeVertex(int index)
    {
        if (indexIsValid(index))
        {
            numVertices--;

            for (int i = index; i < numVertices; i++)
            {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++)
            {
                for (int j = 0; j < numVertices; j++)
                {
                    adjMatrix[i][j] = adjMatrix[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++)
            {
                for (int j = 0; j < numVertices; j++)
                {
                    adjMatrix[i][j] = adjMatrix[i][j + 1];
                }
            }
        }
    }

    /**
     * Retorna true se verificar que o indice é valido, false caso contrário
     * @param index a ser verificado
     * @return true se verificar que o indice é valido, false caso contrário
     */
    private boolean indexIsValid(int index)
    {
        return ((index < numVertices) && index >= 0);
    }

    @Override
    public void addEdge(T vertex1, T vertex2)
    {
        addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Retorna o indice do vértice
     * @param vertex a ser procurado
     * @return o indice do vértice
     */
    private int getIndex(T vertex)
    {
        for (int i = 0; i < numVertices; i++)
        {
            if (vertices[i].equals(vertex))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retorna "Successful" se conseguir adicionar uma aresta entre os dois vértices deste grafo
     * @param index1 o indice do primeiro vértice
     * @param index2 o indice do segundo vértice
     * @return "Successful" se conseguir adicionar uma aresta entre os dois vértices deste grafo
     */
    private String addEdge(int index1, int index2)
    {
        if (!indexIsValid(index1))
        {
            throw new IllegalArgumentException("First vertex isn't valid");
        }

        if (!indexIsValid(index2))
        {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }

        if (indexIsValid(index1) && indexIsValid(index2))
        {
            adjMatrix[index1][index2] = true;
            adjMatrix[index2][index1] = true;

            return "Successful";
        }

        return "Successful";
    }

    @Override
    public void removeEdge(T vertex1, T vertex2)
    {
        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    public String removeEdge(int index1, int index2)
    {
        if (!indexIsValid(index1))
        {
            throw new IllegalArgumentException("First vertex isn't valid");
        }

        if (!indexIsValid(index2))
        {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }


        if (indexIsValid(index1) && indexIsValid(index2))
        {
            adjMatrix[index1][index2] = false;
            adjMatrix[index2][index1] = false;

            return "Successful";
        }

        return "Successful";
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex)
    {
        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que executa uma travessia de pesquisa em largura começando no índice fornecido
     * @param startIndex o índice a partir do qual iniciar a pesquisa
     * @return um iterador que executa uma primeira travessia em largura
     */
    public Iterator<T> iteratorBFS(int startIndex)
    {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex))
        {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty())
        {
            try
            {
                x = traversalQueue.dequeue();
                resultList.addToRear(vertices[x]);

                //encontre todos os vértices adjacentes a x que não foram visitados e coloque-os na fila
                for (int i = 0; i < numVertices; i++)
                {
                    if (adjMatrix[x][i] && !visited[i])
                    {
                        traversalQueue.enqueue(i);
                        visited[i] = true;
                    }
                }
            } catch (EmptyCollectionException ex)
            {
                System.out.println(ex.getMessage());
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex)
    {
        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Retorna um iterador que executa uma travessid de pesquisa em profundidade começando no indice fornecido
     * @param startIndex o índice a partir do qual iniciar a pesquisa
     * @return um iterador que executa uma travessid de pesquisa em profundidade começando no indice fornecido
     */
    private Iterator<T> iteratorDFS(int startIndex)
    {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex))
        {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty())
        {
            try
            {
                x = traversalStack.peek();
                found = false;

                //encontre um vértice adjacente a x que não foi visitado e coloque-o na pilha
                for (int i = 0; (i < numVertices) && !found; i++)
                {
                    if (adjMatrix[x][i] && !visited[i])
                    {
                        traversalStack.push(i);
                        resultList.addToRear(vertices[i]);
                        visited[i] = true;
                        found = true;
                    }
                }
                if (!found && !traversalStack.isEmpty())
                {
                    traversalStack.pop();
                }
            } catch (NullException ex)
            {
                Logger.getLogger(MatrixGraph.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
    {
        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Retorna um iterador que contém o caminho mais curto entre os dois vértices
     * @param startIndex o indice do vértice inicial
     * @param targetIndex o indice do vértice final
     * @return um iterador que contém o caminho mais curto entre os dois vértices
     */
    private Iterator<T> iteratorShortestPath(int startIndex, int targetIndex)
    {
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex))
        {
            return resultList.iterator();
        }

        Iterator<Integer> it;

        try
        {
            it = iteratorShortestPathIndices(startIndex, targetIndex);

            while (it.hasNext())
            {
                resultList.addToRear(vertices[it.next()]);
            }

        } catch (EmptyCollectionException | NullException e)
        {
            e.printStackTrace();
        }

        return resultList.iterator();
    }


    /**
     * Retorna um iterador que contém os índices do caminho mais curto entre dois vértices
     * @param startIndex o indice do vértice inicial
     * @param targetIndex o indice do vértice final
     * @return um iterador que contém os índices do caminho mais curto entre dois vértices
     * @throws EmptyCollectionException
     * @throws NullException
     */
    private Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException, NullException
    {
        int index = startIndex;
        int[] pathLength = new int[numVertices];
        int[] predecessor = new int[numVertices];
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<Integer> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex) || (startIndex == targetIndex))
        {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        pathLength[startIndex] = 0;
        predecessor[startIndex] = -1;

        while (!traversalQueue.isEmpty() && (index != targetIndex))
        {
            index = (traversalQueue.dequeue());

            //Atualiza o pathLength para cada vértice não visitado adjacente ao vértice no índice atual
            for (int i = 0; i < numVertices; i++)
            {
                if (adjMatrix[index][i] && !visited[i])
                {
                    pathLength[i] = pathLength[index] + 1;
                    predecessor[i] = index;
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        //nenhum caminho deve ter sido encontrado
        if (index != targetIndex)
        {
            return resultList.iterator();
        }

        LinkedStack<Integer> stack = new LinkedStack<>();
        index = targetIndex;
        stack.push(index);

        do
        {
            index = predecessor[index];
            stack.push(index);

        } while (index != startIndex);

        while (!stack.isEmpty())
        {
            resultList.addToRear(stack.pop());
        }

        return resultList.iterator();
    }


    @Override
    public boolean isEmpty()
    {
        return (this.numVertices == 0);
    }

    @Override
    public boolean isConnected()
    {
        if (isEmpty())
        {
            return false;
        }

        Iterator<T> it = iteratorBFS(0);
        int count = 0;

        while (it.hasNext())
        {
            it.next();
            count++;
        }

        return (count == numVertices);
    }

    @Override
    public int size()
    {
        return this.numVertices;
    }

    @Override
    public String toString()
    {
        if (numVertices == 0)
        {
            return "Graph is empty";
        }

        String result = "";

        result += "\n\t\tAdjacency Matrix\n";
        result += "\t\t-----------------------------------------\n";
        result += "\t\tindex\t";

        for (int i = 0; i < numVertices; i++)
        {
            result += " " + i;
        }

        result += "\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";

            for (int j = 0; j < numVertices; j++)
            {
                if(adjMatrix[i][j])
                {
                    result += "1 ";

                } else
                {
                    result += "0 ";
                }
            }

            result += "\n";
        }

        result += "\nVertex Values";
        result += "\n-------------\n";
        result += "index\tvalue\n\n";

        for (int i = 0; i < numVertices; i++)
        {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        return result;
    }
}
