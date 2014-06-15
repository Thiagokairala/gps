package model;

public class HeapNode {
	private Node node;
	private Edge edgeUsed;
	private float distance;

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Edge getEdgeUsed() {
		return edgeUsed;
	}

	public void setEdgeUsed(Edge edgeUsed) {
		this.edgeUsed = edgeUsed;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}
}
