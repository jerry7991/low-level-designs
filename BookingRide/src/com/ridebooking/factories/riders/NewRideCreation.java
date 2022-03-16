package com.ridebooking.factories.riders;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.RideFactory;
import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.MaxSheetExceedException;
import com.ridebooking.exceptions.ResourceNotFoundException;
import com.ridebooking.exceptions.RiderIdConflictException;

public class NewRideCreation implements RideFactory {
	private BufferedReader reader;

	public NewRideCreation() {
		reader = Constant.reader;
	}

	@Override
	public void execute(RiderActivity riderActicity) {
		try {
			System.out.println(
					"*********Enter Details for create new ride (name/riderId/ driverId/origin/dest/no of seats)********** ");
			String[] input = reader.readLine().split(" ");
			try {
				riderActicity.createRide(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2]),
						Integer.parseInt(input[3]), Integer.parseInt(input[4]), Integer.parseInt(input[5]));
			} catch (NumberFormatException | BadInputException | RiderIdConflictException | MaxSheetExceedException
					| ResourceNotFoundException e) {
				System.out.print("Error :: " + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}

}
