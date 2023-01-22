package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.interfaces.GraphADT;
import collections.interfaces.UnorderedListADT;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Linked implementation of {@link GraphADT graph contract}.
 *
 * @param <T> Type to be managed.
 */
public class Graph<T> implements GraphADT<T> {

    /**
     * Default initial capacity.
     */
    protected final int DEFAULT_CAPACITY = 10;

    /**
     * Number of vertices in the graph.
     */
    protected int numVertices;

    /**
     * Adjacency matrix.
     */
    protected double[][] adjMatrix;

    /**
     * Vertices value.
     */
    protected T[] vertices;


    /**
     * Creates an empty graph.
     */
    @SuppressWarnings("unchecked")
    public Graph() {
        this.numVertices = 0;
        this.adjMatrix = new double[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * "Expands" the matrix adjacency and array of vertices capacity to the double actual size.
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] verticesTmp = (T[]) (new Object[this.numVertices * 2]);
        double[][] adjMatrixTmp = new double[this.numVertices * 2][this.numVertices * 2];

        for (int i = 0; i < this.numVertices; i++) {
            verticesTmp[i] = this.vertices[i];
            for (int j = 0; j < this.numVertices; j++) {
                adjMatrixTmp[i][j] = this.adjMatrix[i][j];
            }
        }
        this.vertices = verticesTmp;
        this.adjMatrix = adjMatrixTmp;
    }

    @Override
    public boolean addVertex(T vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException("Null vertex");
        }
        boolean flag = false;
        if (numVertices == vertices.length) {
            expandCapacity();
        }
        if (this.getIndex(vertex) == -1) { //If vertex is not repeated.
            vertices[numVertices] = vertex;
            for (int i = 0; i <= numVertices; i++) {
                this.adjMatrix[numVertices][i] = 0;
                adjMatrix[i][numVertices] = 0;
            }
            numVertices++;
            flag = true;
        }
        return flag;
    }

    @Override
    public void removeVertex(T vertex) throws NoSuchElementException {
        int index = this.getIndex(vertex);

        if (index == -1) {
            throw new NoSuchElementException("removing on graph");
        }

        int i;
        while (index < this.numVertices) {
            // Shift all vertices.
            this.vertices[index] = this.vertices[index + 1];

            // Shifting one rows from index to last.
            for (i = 0; i < this.numVertices; i++) {
                this.adjMatrix[i][index] = this.adjMatrix[i][index + 1];
            }

            // Shifting one column from index to last.
            for (i = 0; i < this.numVertices; i++) {
                this.adjMatrix[index][i] = this.adjMatrix[index + 1][i];
            }
            index++;
        }
        this.numVertices--;
    }

    /**
     * Gets the vertex index.
     *
     * @param vertex to be searched.
     * @return index of vertex.
     */
    protected int getIndex(T vertex) {
        int index = -1;
        for (int i = 0; i < this.numVertices && index == -1; i++) {
            if (this.vertices[i].equals(vertex)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void addEdge(T vertex1, T vertex2) {
        this.addEdge(getIndex(vertex1), getIndex(vertex2));
    }

    private void addEdge(int index1, int index2) {
        if (!indexIsValid(index1)) {
            throw new IllegalArgumentException("First vertex isn't valid");
        }
        if (!indexIsValid(index2)) {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }
        adjMatrix[index1][index2] = 1;
        adjMatrix[index2][index1] = 1;
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        this.removeEdger(getIndex(vertex1), getIndex(vertex2));
    }

    public void removeEdger(int index1, int index2) {
        if (!indexIsValid(index1)) {
            throw new IllegalArgumentException("First vertex isn't valid");
        }
        if (!indexIsValid(index2)) {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }
        if (adjMatrix[index1][index2] == 0 || adjMatrix[index2][index1] == 0) {
            throw new IllegalArgumentException("Doesn't exist edges between the two vertex");
        }
        adjMatrix[index1][index2] = 0;
        adjMatrix[index2][index1] = 0;
    }

    /**
     * Checks if index is valid.
     *
     * @param index to be checked.
     * @return true if it's valid, false otherwise.
     */
    protected boolean indexIsValid(int index) {
        return index != -1 && this.vertices[index] != null;
    }

    @Override
    public Iterator<T> iteratorBFS(T startVertex) {
        int index = this.getIndex(startVertex);
        if (index == -1) {
            throw new NoSuchElementException("graph BFS iterator");
        }
        return this.iteratorBFS(index);
    }

    /**
     * Returns an iterator that performs a breadth first search
     * traversal starting at the given index.
     *
     * @param startIndex the index to begin the search from
     * @return an iterator that performs a breadth first traversal
     */
    private Iterator<T> iteratorBFS(int startIndex) {
        Integer actualIndex;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        while (!traversalQueue.isEmpty()) {
            actualIndex = traversalQueue.dequeue();
            resultList.addToRear(vertices[actualIndex]);
            /* Find all vertices adjacent to x that have
             not been visited and queue them up */
            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[actualIndex][i] != 0 && !visited[i]) {
                    traversalQueue.enqueue(i);
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorDFS(T startVertex) {
        int index = this.getIndex(startVertex);
        if (index == -1) {
            throw new NoSuchElementException("graph DFS iterator");
        }
        return this.iteratorDFS(index);
    }

    /**
     * Returns an iterator that performs a depth first search
     * traversal starting at the given index.
     *
     * @param startIndex the index to begin the search traversal from
     * @return an iterator that performs a depth first traversal
     */
    private Iterator<T> iteratorDFS(int startIndex) {
        Integer actualIndex;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<>();
        boolean[] visited = new boolean[numVertices];
        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(startIndex);
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;
        while (!traversalStack.isEmpty()) {
            actualIndex = traversalStack.peek();
            found = false;
            /* Find a vertex adjacent to x that has not been visited
             and push it on the stack */
            for (int i = 0; (i < numVertices) && !found; i++) {
                if (adjMatrix[actualIndex][i] != 0 && !visited[i]) {
                    traversalStack.push(i);
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex) {
        int actualIndex;
        boolean found = false; //Will alert if we had reached the target vertex

        int startIndex = this.getIndex(startVertex);
        int targetIndex = this.getIndex(targetVertex);

        LinkedQueue<Integer> traversalQueue = new LinkedQueue<>();

        int[] distance = new int[this.numVertices]; //Sets the distance from source to node.
        int[] indexOfPrevious = new int[this.numVertices]; //Sets the previous index from the specific node.
        boolean[] visited = new boolean[numVertices]; //Says if the node was visited.

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        //Initialization for the start vertex
        traversalQueue.enqueue(startIndex);
        visited[startIndex] = true;
        distance[startIndex] = 0; //The node distance to itself its 0
        indexOfPrevious[startIndex] = -1; //Sets the last index (stopping point)

        while (!found && !traversalQueue.isEmpty()) {
            actualIndex = traversalQueue.dequeue();
            for (int i = 0; i < numVertices; i++) {
                if (this.adjMatrix[actualIndex][i] != 0 && !visited[i]) { //For all the non visited neighbors
                    traversalQueue.enqueue(i);
                    distance[i] = distance[actualIndex] + 1;
                    visited[i] = true;
                    indexOfPrevious[i] = actualIndex;

                    if (i == targetIndex) { //If the node it's the target, we can stop the search
                        found = true;
                    }
                }
            }
        }
        UnorderedListADT<T> resultList = new DoubleLinkedUnorderedList<>();
        /*
        After we had all the previous list completed, we need to make a reserve traversal from the
        array and adding to the front of a list, with the objective to have the shortest path.
         */
        int currentIndex = targetIndex;
        while (indexOfPrevious[currentIndex] != -1) {
            resultList.addToFront(this.vertices[currentIndex]);
            currentIndex = indexOfPrevious[currentIndex];
        }
        resultList.addToFront(startVertex);

        return resultList.iterator();
    }


    @Override
    public boolean isEmpty() {
        return this.numVertices == 0;
    }

    @Override
    public boolean isConnected() throws EmptyCollectionException {
        boolean[] visited = new boolean[this.numVertices];
        if (this.isEmpty()) {
            throw new EmptyCollectionException("Graph is empty");
        }

        Iterator<T> iterator = this.iteratorDFS(this.vertices[0]);
        while (iterator.hasNext()) {
            visited[this.getIndex(iterator.next())] = true;
        }

        int count = 0;
        for (int i = 0; i < numVertices; i++) {
            if (visited[i]) {
                count++;
            }
        }
        return count == this.numVertices;
    }

    @Override
    public int size() {
        return this.numVertices;
    }

    @Override
    public String toString() {
        String string = "";

        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                string += this.adjMatrix[i][j] + " ";
            }
            string += "\n";
        }
        return string;
    }
}
