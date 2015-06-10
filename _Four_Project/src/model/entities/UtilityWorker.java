package model.entities;

public enum UtilityWorker {//коммунальный работник
	SANITARY_TECHNICIAN(2700000), ELECTRICIAN(3600000), JOINER(1800000), DISPATCHER;
	
	private long timeForWork;

	private UtilityWorker(long timeForWork) {
		this.timeForWork = timeForWork;
	}
	private UtilityWorker() {
	}

	/**
	 * @return the timeForWork
	 */
	public long getTimeForWork() {
		return timeForWork;
	}

	/**
	 * @param timeForWork the timeForWork to set
	 */
	public void setTimeForWork(long timeForWork) {
		this.timeForWork = timeForWork;
	}
}
