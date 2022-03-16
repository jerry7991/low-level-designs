package com.ridebooking.service;

import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.entity.Driver;
import com.ridebooking.entity.Ride;
import com.ridebooking.enums.DriverStatus;
import com.ridebooking.enums.RideStatus;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.MaxSheetExceedException;
import com.ridebooking.exceptions.ResourceNotFoundException;
import com.ridebooking.exceptions.RiderIdConflictException;
import com.ridebooking.repository.DriverRepository;
import com.ridebooking.repository.RidersRepository;

public class RiderActivityImpl implements RiderActivity {

	private RidersRepository ridersRepository;
	private DriverRepository driverRepository;

	public RiderActivityImpl() {
		ridersRepository = RidersRepository.getInstance();
		driverRepository = DriverRepository.getInstance();
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
	public void createRide(String name, int id, int driverId, int origin, int dest, int noOfSeats)
			throws BadInputException, RiderIdConflictException, MaxSheetExceedException, ResourceNotFoundException {

		if (origin >= dest) {
			throw new BadInputException("Origin is greater than dest. Can't create the ride");
		}
		if (ridersRepository.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id already used!");
		}

		Driver driver = driverRepository.getDriverById(driverId);

		if (driver == null || driver.getDriverStatus() == DriverStatus.NOT_AVAILABLE) {
			throw new ResourceNotFoundException("Desired drive is not available/exist right now.");
		}

		if (driverRepository.getDriverById(driverId).getAvailableSheat() < noOfSeats) {
			throw new MaxSheetExceedException("Given driver has not enough sheet.");
		}

		driver.setDriverStatus(DriverStatus.BOOKED);
		driverRepository.updateSeatById(driverId, noOfSeats);
		Ride ride = new Ride(name, id, driverId, origin, dest, noOfSeats, RideStatus.CREATED,
				ridersRepository.isPriorityRider(id));
		ridersRepository.addRiders(ride);
	}

	@Override
	public void updateRide(int id, int origin, int dest, int noOfSeats)
			throws RiderIdConflictException, BadInputException, MaxSheetExceedException {

		if (origin >= dest) {
			throw new BadInputException("Origin is greater than dest. Can't create the ride");
		}

		if (ridersRepository.isCompletedRide(id)) {
			throw new RiderIdConflictException("Given Id's ride has been completed.");
		}

		if (!ridersRepository.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id doesn't exist!");
		}

		Ride ride = ridersRepository.findRideById(id);
		int availableSheat = driverRepository.getDriverById(ride.getDriverId()).getAvailableSheat();
		if (availableSheat < noOfSeats) {
			throw new MaxSheetExceedException("Given driver has not enough sheet.");
		}
		driverRepository.updateSeatById(ride.getDriverId(), noOfSeats - ride.getSeats());
		ride.setOrigin(origin);
		ride.setDest(dest);
		ride.setSeats(noOfSeats);
	}

	@Override
	public void withdrawRide(int id) throws BadInputException, RiderIdConflictException {
		if (ridersRepository.isCompletedRide(id)) {
			throw new BadInputException("Rides already completed.");
		}

		if (!ridersRepository.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id doesn't exist!");
		}

		Ride ride = ridersRepository.getRiderById(id);
		driverRepository.updateSeatById(ride.getDriverId(), -1 * ride.getSeats());
		ridersRepository.deleteRideById(id);
	}

	@Override
	public int closeRide(int id) throws BadInputException, RiderIdConflictException {
		if (!ridersRepository.isRunningRide(id)) {
			throw new RiderIdConflictException("Given rider id doesn't exist any active ride!");
		}
		Ride ride = ridersRepository.findRideById(id);
		int fare = calculateFare(ride);
		ridersRepository.markCompletedRide(ride);
		driverRepository.updateSeatById(ride.getDriverId(), -1 * ride.getSeats());
		return fare;
	}

	@Override
	public void showAllActiveUser() {
		ridersRepository.showAllActiveUser();
	}

}
