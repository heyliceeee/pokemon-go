package collections.interfaces;

import collections.exceptions.EmptyCollectionException;
import collections.implementation.Graph;
import collections.implementation.LinkedBinaryTree;

import java.util.Iterator;

/**
 * Interface of exporter.
 */
public interface IExporter {

    /**
     * Sets new directory.
     *
     * @param directory to be updated.
     */
    void setDirectory(String directory);

    /**
     * Gets the actual directory of exporter.
     *
     * @return actual directory of exporter.
     */
    String getDirectory();

    /**
     * Method that exports a {@link LinkedBinaryTree LinkedBinaryTree} to a .dot file and png representation.
     *
     * @param tree     instance of {@link LinkedBinaryTree LinkedBinaryTree} to be exported.
     * @param filename name of file to be created on directory. Attention: if file name already exists,
     *                 the content will be overwritten.
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
    <T> void exportLinkedBinaryTreeToGraph(LinkedBinaryTree<T> tree, String filename) throws EmptyCollectionException, InterruptedException;

    /**
     * Method that exports a {@link Graph intance of graph} to a .dot file and png representation.
     *
     * @param graph    instance of {@link Graph unweighted graph} to be exported.
     * @param filename name of file to be created on directory. Attention: if file name already exists,
     *                 the content will be overwritten.
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
    /*<T> void exportGraph(GraphADT<T> graph, String filename) throws EmptyCollectionException, InterruptedException;*/


    /**
     * Method that exports a {@link Graph intance of graph} and a path to a .dot file and png representation.
     *
     * @param graph        instance of {@link Graph unweighted graph} to be exported.
     * @param pathIterator path to be noticed on graph.
     * @param filename     name of file to be created on directory. Attention: if file name already exists,
     *                     the content will be overwritten.
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
//    <T> void exportPathGraph(GraphADT<T> graph, Iterator<T> pathIterator, String filename) throws InterruptedException, EmptyCollectionException;

    /**
     * Method that exports a path described in a iterator to a .dot file and png representation.
     *
     * @param pathIterator path to be drawn.
     * @param filename     name of file to be created on directory. Attention: if file name already exists,
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
    /*<T> void exportPath(Iterator<T> pathIterator, String filename) throws EmptyCollectionException, InterruptedException;*/
}
