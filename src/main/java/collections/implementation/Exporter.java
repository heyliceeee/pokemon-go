package collections.implementation;

import collections.exceptions.EmptyCollectionException;
import collections.interfaces.GraphADT;
import collections.interfaces.IExporter;
import collections.interfaces.QueueADT;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Class that implements interface IExporter.
 */
public class Exporter implements IExporter {

    /**
     * Directory (from the root of project) to store the content.
     */
    private String directory;

    /**
     * Constructor of export instance.
     *
     * @param directory to be stored the export content.
     */
    public Exporter(String directory) {
        if (directory == null || directory.equals("")) {
            throw new IllegalArgumentException("Directory name invalid!");
        }
        this.directory = directory;
    }

    @Override
    public void setDirectory(String directory) {
        if (directory == null || directory.equals("")) {
            throw new IllegalArgumentException("Directory name invalid!");
        }
        this.directory = directory;
    }

    @Override
    public String getDirectory() {
        return this.directory;
    }

    @Override
    public <T> void exportLinkedBinaryTreeToGraph(LinkedBinaryTree<T> tree, String filename) throws EmptyCollectionException, InterruptedException {
        if (tree.isEmpty()) {
            throw new EmptyCollectionException("Tree is empty");
        }
        QueueADT<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        //Make a lever order traverse and write into string the content of .dot graph
        String content = "digraph{\n\tgraph [ordering=\"out\"]";

        nodes.enqueue(tree.root);

        BinaryTreeNode<T> current;
        while (!nodes.isEmpty()) {
            current = nodes.dequeue();
            if (current.left != null) {
                nodes.enqueue(current.left);
                content += current.element.toString() + "->" + current.left.element.toString() + "[label=\"L\"]\n";
            }
            if (current.right != null) {
                nodes.enqueue(current.right);
                content += current.element.toString() + "->" + current.right.element.toString() + "[label=\"R\"]\n";
            }
        }
        content += "\n}";

        this.makeDotAndPng(content, filename);
    }

    /*@Override
    public <T> void exportGraph(GraphADT<T> graph, String filename) throws EmptyCollectionException, InterruptedException {
        this.makeDotAndPng(this.exportGraph(graph), filename);
    }*/

    /*@Override
    public <T> void exportPathGraph(GraphADT<T> graph, Iterator<T> pathIterator, String filename) throws EmptyCollectionException, InterruptedException {
        this.makeDotAndPng(this.exportPathGraph(graph, pathIterator), filename);
    }*/

    /*public <T> void exportPath(Iterator<T> pathIterator, String filename) throws EmptyCollectionException, InterruptedException {
        this.makeDotAndPng(this.exportPath(pathIterator), filename);
    }*/

    /**
     * Make the string .dot file.
     *
     * @param graph instance of {@link Graph unweighted graph} to be exported.
     *              the content will be overwritten.
     * @return string of .dot.
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
    /*private <T> String exportGraph(GraphADT<T> graph) throws EmptyCollectionException, InterruptedException {
        if (graph.isEmpty()) {
            throw new EmptyCollectionException("Graph is empty");
        }
        Graph<T> actualGraph = (Graph<T>) graph;
        String content = "strict digraph{\n\tgraph [ordering=\"out\"]";

        if (!graph.isConnected()) { //If graph is not connected will write all vertex first
            for (int i = 0; i < actualGraph.numVertices - 1; i++) {
                if (graph instanceof PathNetwork) {
                    Place place = (Place) actualGraph.vertices[i];
                    content += "\"" + place.getName() + "\"" + ",";
                } else {
                    content += "\"" + actualGraph.vertices[i] + "\"" + ",";
                }
            }
            if (graph instanceof PathNetwork) {
                Place place = (Place) actualGraph.vertices[actualGraph.numVertices - 1];
                content += "\"" + place.getName() + "\"\n";
            } else {
                content += "\"" + actualGraph.vertices[actualGraph.numVertices - 1] + "\"\n";
            }
        }
        //For all vertices makes the edges
        for (int i = 0; i < actualGraph.numVertices; i++) {
            for (int j = i; j < actualGraph.numVertices; j++) {
                if (actualGraph.adjMatrix[i][j] != 0) {
                    if (graph instanceof PathNetwork) {
                        Place placeOne = (Place) actualGraph.vertices[i];
                        Place placeTwo = (Place) actualGraph.vertices[j];
                        content += "\"" + placeOne.getName() + "\"->" + "\"" + placeTwo.getName() + "\"" +
                                "[arrowhead=none][label=" + actualGraph.adjMatrix[i][j] + "]\n";
                    } else if (actualGraph instanceof Network) {
                        content += "\"" + actualGraph.vertices[i].toString() + "\"->" + "\"" + actualGraph.vertices[j].toString() + "\"" +
                                "[arrowhead=none][label=" + actualGraph.adjMatrix[i][j] + "]\n";
                    } else {
                        content += "\"" + actualGraph.vertices[i].toString() + "\"->" + "\"" + actualGraph.vertices[j].toString() + "\"" +
                                "[arrowhead=none]\n";
                    }
                }
            }
        }
        content += "\n}";
        return content;
    }*/

    /**
     * Make the string .dot file.
     *
     * @param graph        instance of {@link Graph unweighted graph} to be exported.
     *                     the content will be overwritten.
     * @param pathIterator path to be noticed on graph.
     * @return string of .dot.
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
   /* private <T> String exportPathGraph(GraphADT<T> graph, Iterator<T> pathIterator) throws EmptyCollectionException, InterruptedException {
        String content = this.exportGraph(graph);
        content = content.substring(0, content.length() - 2); //Remove last two characters, the "\n}" final of file

        //After we have the graph done we will make the path edges
        T first = null;
        T second = null;
        PathNetwork<IPlace> tmpGraph;
        while (pathIterator.hasNext()) {
            if (first == null) { //If it's the first entry on loop.
                first = pathIterator.next();
                if (graph instanceof PathNetwork) {
                    Place placeOne = (Place) first;
                    content += "\"" + placeOne.getName() + "\"[fillcolor=red, style=\"rounded,filled\"]\n";
                } else {
                    content += "\"" + first.toString() + "\"[fillcolor=red, style=\"rounded,filled\"]\n";
                }
            } else { //If not will assume the value of old second.
                first = second;
            }
            if (pathIterator.hasNext()) {
                second = pathIterator.next();
                Place placeOne;
                Place placeTwo;
                if (graph instanceof PathNetwork) {
                    tmpGraph = (PathNetwork<IPlace>) graph;
                    *//*
                    Because of .dot specifications, it's different the edge A->B and B->A, so it's necessary to check
                    what is the correct order of edge written before, and the order it's the small index in the left and the
                    bigger on the right.
                     *//*
                    if (tmpGraph.getIndex((IPlace) first) <= tmpGraph.getIndex((IPlace) second)) {
                        placeOne = (Place) first;
                        placeTwo = (Place) second;
                    } else {
                        placeOne = (Place) second;
                        placeTwo = (Place) first;
                    }
                    content += "\"" + placeOne.getName() + "\"->" + "\"" + placeTwo.getName() + "\"" +
                            "[arrowhead=none][color=red][penwidth = 3]\n";
                } else {
                    content += "\"" + first.toString() + "\"->" + "\"" + second.toString() + "\"" +
                            "[arrowhead=none][color=red][penwidth = 3]\n";
                }
            }
        }
        content += "\n}";
        return content;
    }*/

    /**
     * Makes a string of a .dot file to a path.
     *
     * @param pathIterator path to write.
     * @return string of a .dot file to a path.
     * @throws InterruptedException     will be thrown if user machine doesn't have dot installed on his machine.
     * @throws EmptyCollectionException if collection empty.
     */
    /*private <T> String exportPath(Iterator<T> pathIterator) throws EmptyCollectionException, InterruptedException {
        String content = "digraph{\n\tgraph [ordering=\"in\"]";

        int count = 0;
        T first = null;
        T second = null;
        while (pathIterator.hasNext()) {
            if (first == null) { //If it's the first entry on loop.
                first = pathIterator.next();
            } else { //If not will assume the value of old second.
                first = second;
            }
            if (pathIterator.hasNext()) {
                count++;
                second = pathIterator.next();
                if (first instanceof IPlace) {
                    content += "\"" + ((IPlace) first).getName() + "\"->" + "\"" + ((IPlace) second).getName() + "\"" +
                            "[label=" + count + "]\n";
                } else {
                    content += "\"" + first.toString() + "\"->" + "\"" + second.toString() + "\"" +
                            "[label=" + count + "]\n";
                }
            }
        }
        content += "\n}";
        return content;
    }*/

    /**
     * Creates a .dot file and a png image based on dot file, by executing the command "dot -Tpng 'filename' -O.
     *
     * @param contentString String of .dot content.
     * @param filename      File name of dot document to be created
     * @throws InterruptedException will be thrown if user machine doesn't have dot installed on his machine.
     * @implNote process based on documentation of dot shell.
     */
    private void makeDotAndPng(String contentString, String filename) throws InterruptedException {
        //Make and export a .dot file
        try {
            FileWriter myWriter = new FileWriter(this.directory + "/" + filename + ".dot");
            myWriter.write(contentString);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        /*
        Creates and png image based on dot file, by executing the command
        If the user machine doesn't have dot installed, will be town an expedition.
         */
        String dotFileName = this.directory + "/" + filename + ".dot";
        try {
            String[] c = {"dot", "-Tpng", dotFileName, "-O"}; //command to execute
            Process p = Runtime.getRuntime().exec(c);
            int err = p.waitFor();
        } catch (IOException | InterruptedException e1) {
            System.out.println(e1.getMessage());
        }
        Exporter.showImage(dotFileName + ".png");
    }

    /**
     * Shows an image in a graphical frame.
     *
     * @param imgPath path to image. Needs to contain the full name of image, eg: "docs/image.png"
     */
    public static void showImage(String imgPath) {
        try {
            BufferedImage img = ImageIO.read(new File(imgPath));
            ImageIcon icon = new ImageIcon(img);
            JFrame frame = new JFrame("Visualization");
            frame.setLayout(new FlowLayout());
            frame.setSize(img.getWidth() + 100, img.getHeight() + 100);
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
        } catch (IOException e) {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            e.printStackTrace();
        }
    }
}
