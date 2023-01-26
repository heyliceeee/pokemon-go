package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.interfaces.GraphADT;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * implementação vinculada de {@link GraphADT contrato de grafo}
 * @param <T>
 */
public class Graph<T> implements GraphADT<T>
{
    protected final int DEFAULT_CAPACITY = 10;

    /**
     * número de vértices no grafo
     */
    protected int numVertices;

    /**
     * matriz adjacente
     */
    protected double[][] adjMatrix;

    /**
     * valores dos vértices
     */
    protected T[] vertices;


    /**
     * Criar um grafo vazio
     */
    @SuppressWarnings("unchecked")
    public Graph()
    {
        this.numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }


    /**
     * "expande" a matriz adjacente e a capacidade do array de vértices com o dobro do tamanho
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity()
    {
        T[] verticesTmp = (T[]) (new Object[this.numVertices * 2]);
        double[][] adjMatrixTmp = new double[this.numVertices * 2][this.numVertices * 2];

        for(int i=0; i < this.numVertices; i++)
        {
            verticesTmp[i] = this.vertices[i]; //valor do vértice atual

            for(int j=0; j < this.numVertices; j++)
            {
                adjMatrixTmp[i][j] = this.adjMatrix[i][j];
            }
        }

        this.vertices = verticesTmp;
        this.adjMatrix = adjMatrixTmp;
    }


    @Override
    public boolean addVertex(T vertex)
    {
        if(vertex == null)
        {
            throw new IllegalArgumentException("null vertex");
        }

        boolean added = false;

        if(numVertices == vertices.length)
        {
            expandCapacity();
        }

        if(this.getIndex(vertex) == -1) //se o vértice não é repetido
        {
            vertices[numVertices] = vertex;

            for(int i=0; i <= numVertices; i++)
            {
                adjMatrix[numVertices][i] = 0;
                adjMatrix[i][numVertices] = 0;
            }
            numVertices++;
            added = true;
        }

        return added;
    }


    /**
     * Retorna o índice do vértice
     * @param vertex a ser procurado
     * @return índice do vértice
     */
    protected int getIndex(T vertex)
    {
        for(int i=0; i < numVertices; i++)
        {
            if(this.vertices[i].equals(vertex))
            {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void removeVertex(T vertex) throws NoSuchElementException
    {
        int index = this.getIndex(vertex);

        if(index == -1)
        {
            throw new NoSuchElementException("removing on graph");
        }

        int i;
        while (index < this.numVertices)
        {
            this.vertices[index] = this.vertices[index + 1]; //deslocar todos os vértices

            for (i = 0; i < this.numVertices; i++) //deslocando uma linha do índice para a última
            {
                this.adjMatrix[i][index] = this.adjMatrix[i][index + 1];
            }

            for (i = 0; i < this.numVertices; i++) //deslocando uma coluna do índice para a última
             {
                this.adjMatrix[index][i] = this.adjMatrix[index + 1][i];
            }
            index++;
        }
        this.numVertices--;
    }

    @Override
    public void addEdge(T vertex1, T vertex2)
    {
        this.addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Insere uma aresta entre 2 vértice inteiros
     * @param index1 primeiro vértice
     * @param index2 segundo vértice
     */
    private void addEdge(int index1, int index2)
    {
        if (!indexIsValid(index1)) {
            throw new IllegalArgumentException("First vertex isn't valid");
        }
        if (!indexIsValid(index2)) {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }

        adjMatrix[index1][index2] = 1;
        adjMatrix[index2][index1] = 1;
    }

    /**
     * Verifica se o indice é valido
     * @param index indice a ser verificado
     * @return true se for válido, false caso contrário
     */
    protected boolean indexIsValid(int index)
    {
        return index != -1 && this.vertices[index] != null;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2)
    {
        this.removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Remover a aresta entre 2 vértices inteiros
     * @param index1 primeiro vértice
     * @param index2 segundo vértice
     */
    private void removeEdge(int index1, int index2)
    {
        if (!indexIsValid(index1))
        {
            throw new IllegalArgumentException("First vertex isn't valid");
        }

        if (!indexIsValid(index2))
        {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }

        if (adjMatrix[index1][index2] == 0 || adjMatrix[index2][index1] == 0)
        {
            throw new IllegalArgumentException("Doesn't exist edges between the two vertex");
        }
        adjMatrix[index1][index2] = 0;
        adjMatrix[index2][index1] = 0;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex)
    {
        int index = this.getIndex(startVertex);

        if (index == -1)
        {
            throw new NoSuchElementException("graph BFS iterator");
        }

        return this.iteratorBFS(index);
    }

    /**
     * Retorna um iterador que executa uma travessia de pesquisa em largura começando no índice fornecido
     * @param startIndex índice a partir do qual iniciar a pesquisa
     * @return um iterador que executa uma primeira travessia em largura
     */
    private Iterator<T> iteratorBFS(int startIndex)
    {
        Integer currentIndex;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) //se o índice a partir do qual iniciar a pesquisa
        {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];

        for(int i=0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) //enquanto não for vazio
        {
            currentIndex = traversalQueue.dequeue();
            resultList.addToRear(vertices[currentIndex]);

            for(int i=0; i < numVertices; i++) //encontre todos os vértices adjacentes a "currentIndex" que não foram visitados e coloque-os na queue
            {
                if(adjMatrix[currentIndex][i] != 0 && !visited[i])
                {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex)
    {
        int index = this.getIndex(startVertex);

        if (index == -1)
        {
            throw new NoSuchElementException("graph DFS iterator");
        }

        return this.iteratorDFS(index);
    }


    private Iterator<T> iteratorDFS(int startIndex)
    {
        Integer currentIndex;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex))
        {
            return resultList.iterator();
        }

        for(int i=0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        traversalStack.push(startIndex);

        resultList.addToRear(vertices[startIndex]);

        visited[startIndex] = true;

        while (!traversalStack.isEmpty())
        {
            currentIndex = traversalStack.peek();
            found = false;

            for(int i=0; (i < numVertices) && !found ; i++) //encontre um vértice adjacente a "currentIndex" que não foi visitado e coloque-o na stack
            {
                if(adjMatrix[currentIndex][i] != 0 && !visited[i])
                {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }

            if(!found && !traversalStack.isEmpty()) //se não foi encontrado e a traversalStack não está vazia
            {
                traversalStack.pop();
            }
        }

        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
    {
        int currentIndex;
        boolean found = false; //irá alertar se atingimos o vértice destino

        int startIndex = this.getIndex(startVertex);
        int targetIndex = this.getIndex(targetVertex);

        LinkedQueue<Integer> traversalQueue = LinkedQueue<>();

        int[] distance = new int[this.numVertices]; //define a distância do ponto A ao node
        int[] indexOfPrevious = new int[this.numVertices]; //define o índice anterior do node específico
        boolean[] visited = new boolean[numVertices]; //diz se o node foi visitado

        for(int i=0; i < numVertices; i++)
        {
            visited[i] = false;
        }

        //inicialização para o vértice inicial
        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        distance[startIndex] = 0; //a distância do node para si mesmo é 0
        indexOfPrevious[startIndex] = -1; //define o último indice (ponto de paragem)

        while (!found && !traversalQueue.isEmpty()) //enquanto não é encontrado e o traversalQueue não é vazio
        {
            currentIndex = traversalQueue.dequeue();

            for(int i=0; i < numVertices; i++)
            {
                if(this.adjMatrix[currentIndex][i] != 0 && !visited[i]) //para todos os vizinhos não visitados
                {
                    traversalQueue.enqueue(i);
                    distance[i] = distance[currentIndex] + 1;
                    visited[i] = true;
                    indexOfPrevious[i] = currentIndex;

                    if(i == targetIndex) //se o node for o destino, podemos parar de procurar
                    {
                        found = true;
                    }
                }
            }
        }

        UnorderedListADT<T> resultList = new DoubleLinkedUnorderedList<>();

        //depois de termos completado toda a lista anterior, precisamos fazer um percurso de reserva apartir de
        // um array e adicionar á frente de uma lista, com o objetivo de ter o caminho mais curto

        int indexAtual = targetIndex;

        while (indexOfPrevious[indexAtual] != -1)
        {
            resultList.addToFront(this.vertices[indexAtual]);
            indexAtual = indexOfPrevious[indexAtual];
        }

        resultList.addToFront(startVertex);

        return resultList.iterator();
    }

    @Override
    public boolean isEmpty()
    {
        return this.numVertices == 0;
    }

    @Override
    public boolean isConnected() throws EmptyCollectionException
    {
        boolean[] visited = new boolean[this.numVertices];

        if (this.isEmpty())
        {
            throw new EmptyCollectionException("Graph is empty");
        }

        Iterator<T> iterator = this.iteratorDFS(this.vertices[0]);

        while (iterator.hasNext())
        {
            visited[this.getIndex(iterator.next())] = true;
        }

        int count = 0;
        for (int i = 0; i < numVertices; i++)
        {
            if (visited[i])
            {
                count++;
            }
        }

        return count == this.numVertices;
    }

    @Override
    public int size()
    {
        return this.numVertices;
    }

    @Override
    public String toString()
    {
        String string = "";

        for(int i=0; i < this.numVertices; i++)
        {
            for (int j = 0; j < this.numVertices; j++)
            {
                string += this.adjMatrix[i][j] + " ";
            }
            string += "\n";
        }

        return string;
    }
}
