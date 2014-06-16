package model;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private String className;
    private ClassFloor classFloor;
    private List<Edge> edges;
    private int idNode;
    private int positionOnHeap = -1;

    public Node(String className, ClassFloor classFloor, int idNode) {
	assert (className != null);
	assert (classFloor != null);
	assert (idNode > 0);

	this.className = className;
	this.classFloor = classFloor;
	this.idNode = idNode;
	this.edges = new ArrayList<Edge>();

	assert (this.edges != null);
	assert (this.classFloor.equals(classFloor));
	assert (this.className.equals(className));
	assert (this.idNode == idNode);
    }

    public boolean hasEdges() {
	assert (this.edges != null);

	boolean hasEdges = this.getEdges().isEmpty();

	// changing boolean status.
	return !hasEdges;
    }

    public List<Edge> getEdges() {
	assert (this.edges != null);

	return edges;
    }

    public void setEdges(List<Edge> edges) {
	assert (edges != null);

	this.edges = edges;

	assert (this.edges.equals(edges));
    }

    public int getIdNode() {
	assert (this.getIdNode() >= 0);
	return idNode;
    }

    public void setIdNode(int idNode) {
	assert (idNode >= 0);

	this.idNode = idNode;

	assert (this.idNode == idNode);
    }

    public String getClassName() {
	return className;
    }

    public void setClassName(String className) {
	this.className = className;
    }

    public ClassFloor getClassFloor() {
	return classFloor;
    }

    public void setClassFloor(ClassFloor classFloor) {
	this.classFloor = classFloor;
    }

    public int getPositionOnHeap() {
	return positionOnHeap;
    }

    public void setPositionOnHeap(int positionOnHeap) {
	this.positionOnHeap = positionOnHeap;
    }

}
