package com.ridebooking.api;

import com.ridebooking.entity.Ride;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.MaxSheetExceedException;
import com.ridebooking.exceptions.ResourceNotFoundException;
import com.ridebooking.exceptions.RiderIdConflictException;

public interface RiderActivity {
	int calculateFare(Ride ride);

	void createRide(String name, int id, int driverId, int origin, int dest, int noOfSeats)
			throws BadInputException, RiderIdConflictException, MaxSheetExceedException, ResourceNotFoundException;

	void updateRide(int id, int origin, int dest, int noOfSeats)
			throws BadInputException, RiderIdConflictException, MaxSheetExceedException;

	void withdrawRide(int id) throws BadInputException, RiderIdConflictException;

	int closeRide(int id) throws BadInputException, RiderIdConflictException;

	void showAllActiveUser();
}
