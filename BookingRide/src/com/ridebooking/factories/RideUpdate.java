package com.ridebooking.factories;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.RiderIdConflictException;

public class RideUpdate implements RideFactory {

	BufferedReader reader;

	public RideUpdate() {
		reader = Constant.reader;
	}

	@Override
	public void execute(RiderActivity riderActivity) {
		try {
			System.out.println("********Give Details for update. *******");
			String[] input = reader.readLine().split(" ");
			riderActivity.updateRide(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]),
					Integer.parseInt(input[3]));
		} catch (IOException | NumberFormatException | BadInputException | RiderIdConflictException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}

}
