package com.ridebooking.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ridebooking.entity.Ride;

public class RidersRepository {

	private Map<Integer, Ride> allRides;
	private Map<Integer, List<Ride>> completedRides;
	private static RidersRepository ridersRepository;

	private RidersRepository() {
		allRides = new HashMap<>();
		completedRides = new HashMap<>();
	}

	public static RidersRepository getInstance() {
		if (ridersRepository == null) {
			ridersRepository = new RidersRepository();
		}
		return ridersRepository;
	}

	public boolean isCompletedRide(int id) {
		return this.completedRides.containsKey(id);
	}

	public boolean isRunningRide(int id) {
		return this.allRides.containsKey(id);
	}

	public Ride getRiderById(int id) {
		return allRides.get(id);
	}

	public boolean isPriorityRider(int id) {
		return completedRides.containsKey(id) && completedRides.get(id).size() > 9;
	}

	public void addRiders(Ride ride) {
		allRides.put(ride.getId(), ride);
	}

	public Ride findRideById(int id) {
		return allRides.get(id);
	}

	public void deleteRideById(int id) {
		allRides.remove(id);
	}

	public void markCompletedRide(Ride ride) {
		List<Ride> completed = completedRides.get(ride.getId());
		if (completed == null) {
			completed = new ArrayList<>();
		}
		completed.add(ride);
		completedRides.put(ride.getId(), completed);
		allRides.remove(ride.getId());
	}

	public void showAllActiveUser() {

		System.out.println("------------------------------------");
		System.out.println("\n ******* Active Riders **** \n");
		for (Map.Entry<Integer, Ride> entry : allRides.entrySet()) {
			System.out.println("Id : " + entry.getKey() + " Details => " + entry.getValue());
		}

		System.out.println("\n ******* Completed Trip **** \n");
		for (Entry<Integer, List<Ride>> entry : completedRides.entrySet()) {
			System.out.println(" User Id : " + entry.getKey() + " Details => ");
			entry.getValue().forEach(ride -> System.out.println(ride));
		}

		System.out.println("------------------------------------");
	}
}
