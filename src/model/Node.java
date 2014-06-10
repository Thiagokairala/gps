package model;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private List<Edge> edges;
	private int idNode;

	public Node() {
		this.edges = new ArrayList<Edge>();

		assert (this.edges != null);
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

}
