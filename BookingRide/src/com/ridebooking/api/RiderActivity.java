package com.ridebooking.api;

import com.ridebooking.entity.Ride;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.RiderIdConflictException;

public interface RiderActivity {
	int calculateFare(Ride ride);

	void createRide(int id, int origin, int dest, int noOfSeats) throws BadInputException, RiderIdConflictException;

	void updateRide(int id, int origin, int dest, int noOfSeats) throws BadInputException, RiderIdConflictException;

	void withdrawRide(int id) throws BadInputException, RiderIdConflictException;

	int closeRide(int id) throws BadInputException, RiderIdConflictException;

	void showAllActiveUser();
}
