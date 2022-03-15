package com.ridebooking.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Riders extends Person {

	private Map<Integer, Ride> allRides;
	private Map<Integer, List<Ride>> completedRides;

	public Riders(String name) {
		super(name);
		allRides = new HashMap<>();
		completedRides = new HashMap<>();
	}

	public Map<Integer, Ride> getAllRides() {
		return allRides;
	}

	public void setAllRides(Map<Integer, Ride> allRides) {
		this.allRides = allRides;
	}

	public boolean isCompletedRide(int id) {
		return this.completedRides.containsKey(id);
	}

	public boolean isRunningRide(int id) {
		return this.allRides.containsKey(id);
	}

	public Map<Integer, List<Ride>> getCompletedRides() {
		return completedRides;
	}

	public void setCompletedRides(Map<Integer, List<Ride>> completedRides) {
		this.completedRides = completedRides;
	}

}
