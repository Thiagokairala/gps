package model;

public class Edge {
	private String streetName;
	private Node whoFrom;
	private Node whoTo;
	private float distance;
	private RoadType roadType;
	private boolean hasLights;

	public Edge(String streetName, Node whoFrom, Node whoTo, float distance,
			RoadType roadType, boolean hasLights) {
		assert (streetName != null);
		assert (whoFrom != null);
		assert (whoTo != null);
		assert (distance > 0);
		assert (roadType != null);

		this.streetName = streetName;
		this.whoFrom = whoFrom;
		this.whoTo = whoTo;
		this.distance = distance;
		this.roadType = roadType;
		this.hasLights = hasLights;

		assert (this.streetName.equals(streetName));
		assert (this.whoFrom.equals(whoFrom));
		assert (this.whoTo.equals(whoTo));
		assert (this.distance == distance);
		assert (this.roadType.equals(roadType));
		assert (this.hasLights == hasLights);
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
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

	public RoadType getRoadType() {
		return roadType;
	}

	public void setRoadType(RoadType roadType) {
		this.roadType = roadType;
	}

	public boolean hasLights() {
		return hasLights;
	}

	public void setHasLights(boolean hasLights) {
		this.hasLights = hasLights;
	}

}
