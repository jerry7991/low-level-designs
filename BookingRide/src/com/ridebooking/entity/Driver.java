package com.ridebooking.entity;

import com.ridebooking.enums.DriverStatus;

public class Driver extends Person {

	private int id;
	private int maxSeat;
	private int currSeat;
	private DriverStatus driverStatus;

	public Driver(String name, int id, int maxSeat) {
		super(name);
		this.id = id;
		this.maxSeat = maxSeat;
		this.currSeat = 0;
		this.driverStatus = DriverStatus.IDLE;
	}

	public Driver(String name) {
		super(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxSeat() {
		return maxSeat;
	}

	public void setMaxSeat(int maxSeat) {
		this.maxSeat = maxSeat;
	}

	public int getCurrSeat() {
		return currSeat;
	}

	public void setCurrSeat(int currSeat) {
		this.currSeat = currSeat;
	}

	public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(DriverStatus driverStatus) {
		this.driverStatus = driverStatus;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", maxSeat=" + maxSeat + ", currSeat=" + currSeat + ", driverStatus=" + driverStatus
				+ "]";
	}

	public int getAvailableSheat() {
		return this.maxSeat - currSeat;
	}

}