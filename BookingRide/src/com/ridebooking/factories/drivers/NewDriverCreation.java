package com.ridebooking.factories.drivers;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.DriverActivities;
import com.ridebooking.api.DriverFactory;
import com.ridebooking.constants.Constant;
import com.ridebooking.entity.Driver;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.DriverAlreadyExistException;

public class NewDriverCreation implements DriverFactory {
	private BufferedReader reader;

	public NewDriverCreation() {
		reader = Constant.reader;
	}

	@Override
	public void execute(DriverActivities driverActivities) {
		try {
			System.out.println(
					"************* Enter name without space and driver id and max seat allowed for riders followed by single space***************");
			String[] input = reader.readLine().split(" ");
			driverActivities.addDriver(new Driver(input[0], Integer.parseInt(input[1]), Integer.parseInt(input[2])));
		} catch (IOException | NumberFormatException | DriverAlreadyExistException | BadInputException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}
}