package com.ridebooking.entity;

import com.ridebooking.enums.RideStatus;

public class Ride extends Person {

	private int id;
	private int origin;
	private int driverId;
	private int dest;
	private int seats;
	private RideStatus rideStatus;
	private boolean priorityRiders;

	public Ride(String name, int id, int driverId, int origin, int dest, int seats, RideStatus rideStatus,
			boolean priorityRiders) {
		super(name);
		this.id = id;
		this.origin = origin;
		this.driverId = driverId;
		this.dest = dest;
		this.seats = seats;
		this.rideStatus = rideStatus;
		this.priorityRiders = priorityRiders;
	}

	public Ride(Ride ride) {
		super(ride.getName());
		this.id = ride.id;
		this.driverId = ride.getDriverId();
		this.origin = ride.origin;
		this.dest = ride.dest;
		this.seats = ride.seats;
		this.rideStatus = ride.rideStatus;
		this.priorityRiders = ride.priorityRiders;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrigin() {
		return origin;
	}

	public void setOrigin(int origin) {
		this.origin = origin;
	}

	public int getDest() {
		return dest;
	}

	public void setDest(int dest) {
		this.dest = dest;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public RideStatus getRideStatus() {
		return rideStatus;
	}

	public void setRideStatus(RideStatus rideStatus) {
		this.rideStatus = rideStatus;
	}

	public boolean isPriorityRiders() {
		return priorityRiders;
	}

	public void setPriorityRiders(boolean priorityRiders) {
		this.priorityRiders = priorityRiders;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	@Override
	public String toString() {
		return "Ride [id=" + id + ", origin=" + origin + ", driverId=" + driverId + ", dest=" + dest + ", seats="
				+ seats + ", rideStatus=" + rideStatus + ", priorityRiders=" + priorityRiders + ", getName()="
				+ getName() + "]";
	}

}
