package collections.implementation;

import collections.interfaces.NetworkADT;

/**
 * Class that implements Network contract.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    @Override
    public void addEdge(T vertex1, T vertex2, double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be bigger or equal than 0 in this domain");
        }
        this.addEdge(super.getIndex(vertex1), super.getIndex(vertex2), weight);
    }

    /**
     * Adds edge weight.
     *
     * @param index1 index from vertex 1.
     * @param index2 index from vertex 2-
     * @param weight from edge.
     */
    protected void addEdge(int index1, int index2, double weight) {
        if (!super.indexIsValid(index1)) {
            throw new IllegalArgumentException("First vertex isn't valid");
        }
        if (!super.indexIsValid(index2)) {
            throw new IllegalArgumentException("Second vertex isn't valid");
        }
        super.adjMatrix[index1][index2] = weight;
        super.adjMatrix[index2][index1] = weight;
    }

    @Override
    public double shortestPathWeight(T vertex1, T vertex2) {
        int startVertex = getIndex(vertex1), endVertex = getIndex(vertex2), actualIndex;
        double[] shortestDistances = new double[super.numVertices]; //list of the shortest distances of the all vertices from the beginning
        boolean[] visited = new boolean[super.numVertices]; //list of vertices already visited

        for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            visited[vertexIndex] = false;
        }

        shortestDistances[startVertex] = 0; //the distance of the first node from itself is 0
        double tmpDistance, edgeDistance;
        for (int i = 0; i < super.numVertices && !visited[endVertex]; i++) {
            actualIndex = getSmallDistanceNode(shortestDistances, visited); //get the index of the nearest node
            visited[actualIndex] = true;

            for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
                edgeDistance = super.adjMatrix[actualIndex][vertexIndex]; //get the distance between the near node and all the others nodes
                tmpDistance = shortestDistances[actualIndex] + edgeDistance; //calculate the distance

                if (edgeDistance > 0 && (tmpDistance < shortestDistances[vertexIndex])) {
                    shortestDistances[vertexIndex] = tmpDistance;
                }
            }
        }
        return shortestDistances[endVertex];
    }

    /**
     * Get the index of the node with small distance.
     *
     * @param shortestDistances actual list of distances from source.
     * @param visited           list that have the information about a node was visited or not.
     * @return index of the node with small distance.
     */
    protected int getSmallDistanceNode(double[] shortestDistances, boolean[] visited) {
        int index = -1;
        double shortestDistance = Double.MAX_VALUE;

        for (int vertexIndex = 0; vertexIndex < super.numVertices; vertexIndex++) {
            if (!visited[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) { // take the near node
                index = vertexIndex;
                shortestDistance = shortestDistances[vertexIndex];
            }
        }
        return index;
    }
}