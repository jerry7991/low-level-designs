package com.ridebooking.factories.drivers;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.DriverActivities;
import com.ridebooking.api.DriverFactory;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.ResourceNotFoundException;

public class DriveComplete implements DriverFactory {

	BufferedReader reader;

	public DriveComplete() {
		reader = Constant.reader;
	}

	@Override
	public void execute(DriverActivities driverActivities) {
		System.out.println("******* Give Id for the drive to mark complete drive *********");
		try {
			int id = Integer.parseInt(reader.readLine());
			driverActivities.markCompleteDrive(id);
		} catch (NumberFormatException | IOException | ResourceNotFoundException e) {
			System.out.println("Error :: " + e.getMessage());
		}

	}

}
