package model;

public class Edge {
	private Node whoFrom;
	private Node whoTo;
	private float distance;

	public Edge(Node whoFrom, Node whoTo, float distance) {
		assert (whoFrom != null);
		assert (whoTo != null);
		assert (distance > 0);

		this.whoFrom = whoFrom;
		this.whoTo = whoTo;
		this.distance = distance;

		assert (this.whoFrom.equals(whoFrom));
		assert (this.whoTo.equals(whoTo));
		assert (this.distance == distance);
	}

	public Node getWhoFrom() {
		return whoFrom;
	}

	public void setWhoFrom(Node whoFrom) {
		this.whoFrom = whoFrom;
	}

	public Node getWhoTo() {
		return whoTo;
	}

	public void setWhoTo(Node whoTo) {
		this.whoTo = whoTo;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

}
