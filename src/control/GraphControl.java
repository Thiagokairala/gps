package control;

import java.util.ArrayList;
import java.util.List;

import exception.EmptyHeapException;
import exception.InvalidNodeException;
import model.Edge;
import model.HeapNode;
import model.Node;

public class GraphControl {
    private List<HeapNode> exploredNodes = new ArrayList<HeapNode>();
    private final int EXPLORED = -2;
    private final int NEVER_ON_HEAP = -1;

    public List<HeapNode> findSmallerPath(Node nodeFrom, Node nodeTo)
	    throws InvalidNodeException, EmptyHeapException {

	HeapNode exploringNode = this.createFirstHeapNode(nodeFrom);
	HeapControl heapControl = new HeapControl();
	nodeFrom.setPositionOnHeap(EXPLORED);
	this.putNodeFromOnExploredList(nodeFrom);

	do {
	    this.insertNeighborsOnHeap(exploringNode, heapControl);

	    exploringNode = heapControl.removeFromHeap();
	    exploringNode.getNode().setPositionOnHeap(this.EXPLORED);
	    this.getExploredNodes().add(exploringNode);

	} while (exploringNode.getNode().equals(nodeTo) != true);

	return null;
    }

    private void insertNeighborsOnHeap(HeapNode exploringNode,
	    HeapControl heapControl) throws InvalidNodeException {
	List<Edge> listOfEdges = exploringNode.getNode().getEdges();

	for (int i = 0; i < listOfEdges.size(); i++) {
	    float distance = exploringNode.getDistance()
		    + listOfEdges.get(i).getDistance();

	    if (listOfEdges.get(i).getWhoTo().getPositionOnHeap() != EXPLORED) {
		if (listOfEdges.get(i).getWhoTo().getPositionOnHeap() == NEVER_ON_HEAP) {
		    HeapNode heapNode = this.createHeapNode(listOfEdges.get(i)
			    .getWhoTo(), listOfEdges.get(i), distance);

		    heapControl.insertOnHeap(heapNode);
		} else {
		    heapControl
			    .updateNodeDistance(distance, listOfEdges.get(i)
				    .getWhoTo().getPositionOnHeap(),
				    listOfEdges.get(i));
		}
	    } else {
		// nothing to do.
	    }
	}

    }

    private HeapNode createFirstHeapNode(Node nodeFrom) {
	HeapNode heapNodeToReturn = new HeapNode();
	Edge edge = new Edge(nodeFrom, nodeFrom, 0, 0);

	heapNodeToReturn.setDistance(0);
	heapNodeToReturn.setEdgeUsed(edge);
	heapNodeToReturn.setNode(nodeFrom);

	return heapNodeToReturn;
    }

    private HeapNode createHeapNode(Node heapNode, Edge edgeUsed, float distance) {
	HeapNode heapNodeToReturn = new HeapNode();

	heapNodeToReturn.setDistance(distance);
	heapNodeToReturn.setEdgeUsed(edgeUsed);
	heapNodeToReturn.setNode(heapNode);

	return heapNodeToReturn;
    }

    private void putNodeFromOnExploredList(Node nodeFrom) {
	HeapNode heapNode = new HeapNode();

	heapNode.setDistance(0);
	heapNode.setNode(nodeFrom);

	Edge edge = new Edge(nodeFrom, nodeFrom, 0, 1);

	heapNode.setEdgeUsed(edge);

	this.getExploredNodes().add(heapNode);
    }

    public List<HeapNode> getExploredNodes() {
	return exploredNodes;
    }

    public void setExploredNodes(List<HeapNode> exploredNodes) {
	this.exploredNodes = exploredNodes;
    }

}
