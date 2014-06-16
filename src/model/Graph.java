package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;

    public Graph() {
	this.nodes = new ArrayList<Node>();

	assert (this.nodes != null);
    }

    public void insertOneNode(Node node) {
	assert (this.nodes != null);

	this.nodes.add(node);

	assert (this.nodes.isEmpty() == false);

    }

    public boolean isEmpty() {
	assert (this.nodes != null);

	boolean isEmpty = this.getNodes().isEmpty();
	return isEmpty;
    }

    public List<Node> getNodes() {
	assert (this.nodes != null);

	return nodes;
    }

    public void setNodes(List<Node> nodes) {
	assert (this.nodes != null);

	this.nodes = nodes;

	assert (this.nodes.equals(nodes));
    }
}
