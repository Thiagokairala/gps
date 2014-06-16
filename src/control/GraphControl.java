package control;

import java.util.ArrayList;
import java.util.List;

import exception.InvalidNodeException;
import model.Edge;
import model.HeapNode;
import model.Node;

public class GraphControl {
    private List<HeapNode> exploredNodes;

    public List<HeapNode> findSmallerPath(Node nodeFrom, Node nodeTo) throws InvalidNodeException {

	Node exploringNode = nodeFrom;
	List<HeapNode> smallerPath = new ArrayList<HeapNode>();
	HeapControl heapControl = new HeapControl();

	this.putNodeFromOnExploredList(nodeFrom);

	do {
	    for (int i = 0; i < exploringNode.getEdges().size(); i++) {
		HeapNode heapNode = this.createHeapNode(exploringNode,
			exploringNode.getEdges().get(i));
		heapControl.insertOnHeap(heapNode);

	    }

	} while (!exploringNode.equals(nodeTo));

	return null;
    }

    private HeapNode createHeapNode(Node exploringNode, Edge edgeUsed) {
	HeapNode heapNodeToReturn = new HeapNode();

	heapNodeToReturn.setDistance(edgeUsed.getDistance());
	heapNodeToReturn.setEdgeUsed(edgeUsed);
	heapNodeToReturn.setNode(exploringNode);

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
