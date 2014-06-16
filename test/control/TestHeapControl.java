package control;

import static org.junit.Assert.*;
import model.ClassFloor;
import model.Edge;
import model.Heap;
import model.HeapNode;
import model.Node;

import org.junit.Test;
import org.mockito.Mockito;

import exception.InvalidNodeException;

public class TestHeapControl {

    // @Test
    public void testSwap() {
	HeapNode firstHeapNode = new HeapNode();
	firstHeapNode.setDistance(10);

	HeapNode secondHeapNode = new HeapNode();
	secondHeapNode.setDistance(20);

	HeapNode[] arrayOfHeapNodes = { firstHeapNode, secondHeapNode };

	HeapControl heapControl = new HeapControl();
	HeapControl spyHeapControl = Mockito.spy(heapControl);

	Heap heap = Mockito.mock(Heap.class);

	Mockito.when(heap.getHeap()).thenReturn(arrayOfHeapNodes);

	Mockito.doReturn(heap).when(spyHeapControl).getHeap();

	spyHeapControl.swap(0, 1);

    }

    // this test is commented because the method is private.
    // @Test
    public void testShiftUpHaving2Children() {
	HeapNode parent = new HeapNode();
	HeapNode leftChildren = new HeapNode();
	HeapNode rightChildren = new HeapNode();

	parent.setDistance(20);
	leftChildren.setDistance(25);
	rightChildren.setDistance(5);

	HeapNode[] heapNodeArray = { null, parent, leftChildren, rightChildren };

	HeapControl heapControl = new HeapControl();
	heapControl.getHeap().setHeap(heapNodeArray);

	heapControl.shiftUp(3);

	Heap heap = heapControl.getHeap();

	for (int i = 1; i < heap.getHeapSize(); i++) {
	    System.out.println(heap.getHeap()[i].getDistance());
	}
    }

    // this test is commented because the method is private.
    // @Test
    public void testShiftUpHaving1Children() {
	HeapNode parent = new HeapNode();
	HeapNode leftChildren = new HeapNode();

	parent.setDistance(20);
	leftChildren.setDistance(5);

	HeapNode[] heapNodeArray = { null, parent, leftChildren };

	HeapControl heapControl = new HeapControl();
	heapControl.getHeap().setHeap(heapNodeArray);

	heapControl.shiftUp(2);

	Heap heap = heapControl.getHeap();

	for (int i = 1; i < heap.getHeapSize(); i++) {
	    System.out.println(heap.getHeap()[i].getDistance());
	}
    }

    // this test is commented because the method is private.
    // @Test
    public void testShiftUpHavingToChange2() {
	HeapNode parent = new HeapNode();
	HeapNode leftChildren = new HeapNode();
	HeapNode rightChildren = new HeapNode();

	parent.setDistance(20);
	leftChildren.setDistance(25);
	rightChildren.setDistance(5);

	HeapNode[] heapNodeArray = { null, parent, leftChildren, rightChildren };

	HeapControl heapControl = new HeapControl();
	heapControl.getHeap().setHeap(heapNodeArray);

	heapControl.shiftUp(3);

	Heap heap = heapControl.getHeap();

	for (int i = 1; i < heap.getHeapSize(); i++) {
	    System.out.println(heap.getHeap()[i].getDistance());
	}
    }

    @Test
    public void testInsertOnHeapEmpty() throws Exception {
	HeapControl heapControl = new HeapControl();
	HeapNode nodeToInsert = new HeapNode();

	Edge edge = Mockito.mock(Edge.class);
	Node node = Mockito.mock(Node.class);

	nodeToInsert.setDistance(10);
	nodeToInsert.setEdgeUsed(edge);
	nodeToInsert.setNode(node);

	heapControl.insertOnHeap(nodeToInsert);

	assertTrue(heapControl.getHeap().getHeap()[1].equals(nodeToInsert));
    }

    @Test
    public void testInsertOnPopulatedHeapNoShiftUp()
	    throws InvalidNodeException {
	HeapControl heapControl = new HeapControl();
	HeapNode nodeToInsert = new HeapNode();
	HeapNode firstNode = new HeapNode();

	Edge edge = Mockito.mock(Edge.class);
	Node node = Mockito.mock(Node.class);

	nodeToInsert.setDistance(10);
	nodeToInsert.setEdgeUsed(edge);
	nodeToInsert.setNode(node);

	firstNode.setDistance(5);
	firstNode.setEdgeUsed(edge);
	firstNode.setNode(node);

	HeapNode[] heapNodeArray = { null, firstNode };
	heapControl.getHeap().setHeap(heapNodeArray);

	heapControl.insertOnHeap(nodeToInsert);

	assertTrue(heapControl.getHeap().getHeap()[2].equals(nodeToInsert));
	assertTrue(heapControl.getHeap().getHeap()[1].equals(firstNode));

    }

    @Test
    public void testInsertOnPopulatedHeapShiftUp() throws InvalidNodeException {
	HeapControl heapControl = new HeapControl();
	HeapNode nodeToInsert = new HeapNode();
	HeapNode firstNode = new HeapNode();

	Node node1 = new Node("teste", ClassFloor.FIRST_FLOOR, 1);
	Edge edge = Mockito.mock(Edge.class);
	Node node2 = new Node("teste2", ClassFloor.FIRST_FLOOR, 2);

	nodeToInsert.setDistance(10);
	nodeToInsert.setEdgeUsed(edge);
	nodeToInsert.setNode(node2);

	firstNode.setDistance(15);
	firstNode.setEdgeUsed(edge);
	node1.setPositionOnHeap(1);
	firstNode.setNode(node1);

	HeapNode[] heapNodeArray = { null, firstNode };
	heapControl.getHeap().setHeap(heapNodeArray);

	heapControl.insertOnHeap(nodeToInsert);

	assertTrue(heapControl.getHeap().getHeap()[1].getNode()
		.getPositionOnHeap() == 1);
	assertTrue(heapControl.getHeap().getHeap()[2].getNode()
		.getPositionOnHeap() == 2);

	assertTrue(heapControl.getHeap().getHeap()[2].equals(firstNode));
	assertTrue(heapControl.getHeap().getHeap()[1].equals(nodeToInsert));

    }

    // This test is commented because the method is private.
    // @Test
    public void testHeapify() {
	HeapNode parent = new HeapNode();
	HeapNode leftChildren = new HeapNode();
	HeapNode rightChildren = new HeapNode();

	parent.setDistance(20);
	leftChildren.setDistance(25);
	rightChildren.setDistance(5);

	HeapNode[] heapNodeArray = { null, parent, leftChildren, rightChildren };

	HeapControl heapControl = new HeapControl();
	heapControl.getHeap().setHeap(heapNodeArray);

	heapControl.heapify(1);

	Heap heap = heapControl.getHeap();

	assertTrue(heapControl.getHeap().getHeap()[1].equals(rightChildren));
    }
}
