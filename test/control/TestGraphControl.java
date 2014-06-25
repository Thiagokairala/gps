package control;

import static org.junit.Assert.assertTrue;
import initialization.Init;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import model.ClassFloor;
import model.Edge;
import model.Graph;
import model.HeapNode;
import model.Node;

import org.junit.Test;
import org.xml.sax.SAXException;

import exception.EmptyHeapException;
import exception.InvalidNodeException;

public class TestGraphControl {

    // This test is commented because the method is private.
    // @Test
    public void testInsertNeighborsOnHeap() throws Exception {

	Node first = new Node("test", ClassFloor.FIRST_FLOOR, 0);
	Node second = new Node("test2", ClassFloor.FIRST_FLOOR, 0);
	Node third = new Node("test3", ClassFloor.FIRST_FLOOR, 0);

	Edge edgeFirstToSecond = new Edge(first, second, 10, 0);
	Edge edgeFirstToThird = new Edge(first, third, 20, 0);

	first.getEdges().add(edgeFirstToSecond);
	first.getEdges().add(edgeFirstToThird);

	Edge sentinel = new Edge(first, first, 0, 0);

	HeapNode heapNode = new HeapNode();
	heapNode.setDistance(5);
	heapNode.setEdgeUsed(sentinel);
	heapNode.setNode(first);

	GraphControl graphControl = new GraphControl();
	HeapControl heapControl = new HeapControl();

	graphControl.insertNeighborsOnHeap(heapNode, heapControl);

	assertTrue(heapControl.getHeap().getHeap()[1].getNode().equals(second));
	assertTrue(heapControl.getHeap().getHeap()[1].getDistance() == 15);
	assertTrue(heapControl.getHeap().getHeap()[2].getNode().equals(third));
	assertTrue(heapControl.getHeap().getHeap()[2].getDistance() == 25);

    }

    @Test
    public void testSmallerPath() throws ParserConfigurationException,
	    SAXException, IOException, InvalidNodeException, EmptyHeapException {

	Graph graph = Init.init();

	GraphControl graphControl = new GraphControl();

	List<HeapNode> list = graphControl.findSmallerPath(graph.getNodes()
		.get(0), graph.getNodes().get(15));

	System.out.println("de: " + graph.getNodes().get(0).getClassName());
	System.out.println("para: " + graph.getNodes().get(15).getClassName());

	for (int i = 0; i < list.size(); i++) {
	    System.out.println(list.get(i).getNode().getClassName());
	}

    }
}
