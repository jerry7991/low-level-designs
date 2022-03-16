package com.ridebooking.factories.drivers;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.DriverActivities;
import com.ridebooking.api.DriverFactory;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.InCompleteTaskException;
import com.ridebooking.exceptions.ResourceNotFoundException;

public class StatusChangeToDriver implements DriverFactory {
	BufferedReader reader;

	public StatusChangeToDriver() {
		reader = Constant.reader;
	}

	@Override
	public void execute(DriverActivities driverActivities) {
		System.out.println("****** Enter id to make the driver un-available. ******");
		try {
			int id = Integer.parseInt(reader.readLine());
			driverActivities.makeUnavailableToDriver(id);
		} catch (NumberFormatException | IOException | ResourceNotFoundException | InCompleteTaskException e) {
			System.out.println("Error :::" + e.getMessage());
		}
	}

}
