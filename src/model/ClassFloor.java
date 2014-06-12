package model;

public enum ClassFloor {
	FIRST_FLOOR(1), SECOND_FLOOR(2), FIRST_SECOND_FLOOR(3);

	private int floor;

	private ClassFloor(int floor) {
		this.floor = floor;
	}

	public int getFloor() {
		return this.floor;
	}
}
