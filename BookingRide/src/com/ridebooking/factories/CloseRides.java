package com.ridebooking.factories;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.RiderIdConflictException;

public class CloseRides implements RideFactory {
	private BufferedReader reader;

	public CloseRides() {
		reader = Constant.reader;
	}

	@Override
	public void execute(RiderActivity riderActivity) {
		try {
			System.out.println("****Give id for close the ride. ******");
			int id = Integer.parseInt(reader.readLine());
			System.out.println("You have " + riderActivity.closeRide(id) + " for your ride.");

		} catch (NumberFormatException | IOException | BadInputException | RiderIdConflictException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}

}
