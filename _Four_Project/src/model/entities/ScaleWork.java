package model.entities;

public enum ScaleWork {
PART_OF_THE_ROOM(1), ROOM(2), FLAT(3);

	private int scale;

	
	private ScaleWork(int scale) {
		this.scale = scale;
	}

	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}

	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
}
