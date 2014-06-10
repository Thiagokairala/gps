package model;

public enum RoadType {
	MAIN_ROAD(0), SIDE_ROAD(1), INNER_ROAD(2);

	private int roadType;

	private RoadType(int roadType) {
		this.roadType = roadType;
	}

	public int getRoadType() {
		return this.roadType;
	}
}
