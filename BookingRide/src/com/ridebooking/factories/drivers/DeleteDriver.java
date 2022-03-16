package com.ridebooking.factories.drivers;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.DriverActivities;
import com.ridebooking.api.DriverFactory;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.ResourceNotFoundException;

public class DeleteDriver implements DriverFactory {
	BufferedReader reader;

	public DeleteDriver() {
		reader = Constant.reader;
	}

	@Override
	public void execute(DriverActivities driverActivities) {
		System.out.println("********** Give the driver id for delete operation. ***********");
		try {
			int id = Integer.parseInt(reader.readLine());
			driverActivities.removeDriver(id);
		} catch (NumberFormatException | IOException | ResourceNotFoundException e) {
			System.out.println("Error ::: " + e.getMessage());
		}
	}

}
