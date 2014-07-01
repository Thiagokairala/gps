package main;

import initialization.Init;

import java.io.IOException;
import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import javax.xml.parsers.ParserConfigurationException;

import model.Graph;
import model.HeapNode;
import model.Node;

import org.xml.sax.SAXException;

import control.GraphControl;
import exception.EmptyHeapException;
import exception.InvalidNodeException;
import util.Keyboard;

public class Main {

    public static void main(String[] args) {
	Graph graph = null;

	try {
	    graph = Init.init();
	    System.out.println("Olá, bem vindo ao gps da FGA!");

	    Node[] index = Main.getNumberIndex(graph);

	    GraphControl graphControl = new GraphControl();

	    List<HeapNode> smallerPath = graphControl.findSmallerPath(index[0],
		    index[1]);

	    showSmallerPath(smallerPath);

	} catch (ParserConfigurationException e) {
	    Main.warnError();
	} catch (SAXException e) {
	    Main.warnError();
	} catch (IOException e) {
	    Main.warnError();
	} catch (InvalidNodeException e) {
	    Main.warnError();
	} catch (EmptyHeapException e) {
	    Main.warnError();
	}

    }

    private static void showSmallerPath(final List<HeapNode> smallerPath) {
	final int lastPosition = smallerPath.size() - 1;

	System.out.println("Você está em: "
		+ smallerPath.get(lastPosition).getNode().getClassName());

	System.out.println("Para chegar até seu distino siga este caminho: ");

	for (int i = lastPosition - 1; i > 0; i--) {
	    System.out.println(smallerPath.get(i).getNode().getClassName());
	}

	System.out.println("E finalmente chegará ao seu destino: "
		+ smallerPath.get(0).getNode().getClassName());

    }

    private static Node[] getNumberIndex(final Graph graph) {

	System.out.println("\tEscolha a seguir o numero onde esta: \n");

	Main.showNumbers(graph);

	final int firstNode = Keyboard.getNumber() - 1;

	System.out.println("\n\tEscolha a seguir o numero onde deseja ir: \n");

	Main.showNumbers(graph);

	final int secondNode = Keyboard.getNumber() - 1;

	final Node[] arrayToReturn = Main.getArrayOfNodes(firstNode,
		secondNode, graph);

	return arrayToReturn;
    }

    private static Node[] getArrayOfNodes(final int firstNode,
	    final int secondNode, final Graph graph) {
	Node firstRealNode = graph.getNodes().get(firstNode);
	Node secondRealNode = graph.getNodes().get(secondNode);

	final Node[] arrayOfNodes = { firstRealNode, secondRealNode };

	return arrayOfNodes;
    }

    private static void warnError() {
	System.out.println("Desculpe ouve algum erro inesperado");
    }

    private static void showNumbers(final Graph graph) {
	List<Node> listOfNodes = graph.getNodes();

	for (int i = 0; i < graph.getNodes().size(); i++) {
	    System.out.println((i + 1) + "\t"
		    + listOfNodes.get(i).getClassName());
	}
    }
}
