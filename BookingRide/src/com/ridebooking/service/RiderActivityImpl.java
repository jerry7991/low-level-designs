package com.ridebooking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.entity.Ride;
import com.ridebooking.entity.Riders;
import com.ridebooking.enums.RideStatus;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.RiderIdConflictException;

public class RiderActivityImpl implements RiderActivity {

	private Riders riders;

	public RiderActivityImpl(String name) {
		riders = new Riders(name);
	}

	@Override
	public int calculateFare(Ride ride) {
		int dist = ride.getDest() - ride.getOrigin();
		double fare = 0;
		if (dist < 2) {
			fare = dist * Constant.AMT_PER_KM * (ride.isPriorityRiders() ? 0.75 : 1);
		} else {
			fare = dist * ride.getSeats() * Constant.AMT_PER_KM * (ride.isPriorityRiders() ? 0.5 : 0.75);
		}
		return (int) fare;
	}

	@Override
	public void createRide(int id, int origin, int dest, int noOfSeats)
			throws BadInputException, RiderIdConflictException {

		if (origin >= dest) {
			throw new BadInputException("Origin is greater than dest. Can't create the ride");
		}
		if (riders.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id already used!");
		}
		Ride ride = new Ride(id, origin, dest, noOfSeats, RideStatus.CREATED,
				riders.getCompletedRides().getOrDefault(id, new ArrayList<>()).size() > 9);
		riders.getAllRides().put(id, ride);
	}

	@Override
	public void updateRide(int id, int origin, int dest, int noOfSeats)
			throws RiderIdConflictException, BadInputException {

		if (origin >= dest) {
			throw new BadInputException("Origin is greater than dest. Can't create the ride");
		}

		if (riders.isCompletedRide(id)) {
			throw new RiderIdConflictException("Given Id's ride has been completed.");
		}

		if (!riders.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id doesn't exist!");
		}

		Ride ride = riders.getAllRides().get(id);
		ride.setOrigin(origin);
		ride.setDest(dest);
		ride.setSeats(noOfSeats);

	}

	@Override
	public void withdrawRide(int id) throws BadInputException, RiderIdConflictException {
		if (riders.isCompletedRide(id)) {
			throw new BadInputException("Rides already completed.");
		}

		if (!riders.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id doesn't exist!");
		}

		riders.getAllRides().remove(id);
	}

	@Override
	public int closeRide(int id) throws BadInputException, RiderIdConflictException {
		if (!riders.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id doesn't exist any active ride!");
		}
		int fare = calculateFare(riders.getAllRides().get(id));
		List<Ride> completed = riders.getCompletedRides().get(id);
		if (completed == null) {
			completed = new ArrayList<>();
		}
		completed.add(new Ride(riders.getAllRides().get(id)));
		riders.getCompletedRides().put(id, completed);
		riders.getAllRides().remove(id);
		return fare;
	}

	@Override
	public void showAllActiveUser() {

		System.out.println("------------------------------------");
		System.out.println("\n ******* Active users **** \n");
		for (Map.Entry<Integer, Ride> entry : riders.getAllRides().entrySet()) {
			System.out.println("Id : " + entry.getKey() + " Details => " + entry.getValue());
		}

		System.out.println("\n ******* Completed Trip**** \n");
		for (Entry<Integer, List<Ride>> entry : riders.getCompletedRides().entrySet()) {
			System.out.println(" User Id : " + entry.getKey() + " Details => ");
			entry.getValue().forEach(ride -> System.out.println(ride));
		}

		System.out.println("------------------------------------");
	}

}
