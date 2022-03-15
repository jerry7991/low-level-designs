package com.ridebooking.factories;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.RiderIdConflictException;

public class NewRideCreation implements RideFactory {
	private BufferedReader reader;

	public NewRideCreation() {
		reader = Constant.reader;
	}

	@Override
	public void execute(RiderActivity riderActicity) {
		try {
			System.out.println("*********Enter Details for creat new ride ********** ");
			String[] input = reader.readLine().split(" ");
			try {
				riderActicity.createRide(Integer.parseInt(input[0]), Integer.parseInt(input[1]),
						Integer.parseInt(input[2]), Integer.parseInt(input[3]));
			} catch (NumberFormatException | BadInputException | RiderIdConflictException e) {
				System.out.print("Error :: " + e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}

}
