package com.ridebooking.factories.drivers;

import java.util.HashMap;
import java.util.Map;

import com.ridebooking.api.DriverActivities;
import com.ridebooking.api.DriverFactory;
import com.ridebooking.constants.Constant;
import com.ridebooking.service.DriverActivitiesImpl;

public class DriverFactoryHandler {
	Map<Integer, DriverFactory> factories;
	DriverActivities driverActivities;

	public DriverFactoryHandler() {
		factories = new HashMap<>();
		factories.put(Constant.id++, new NewDriverCreation());
		factories.put(Constant.id++, new StatusChangeToDriver());
		factories.put(Constant.id++, new DeleteDriver());
		factories.put(Constant.id++, new DriveComplete());
		driverActivities = new DriverActivitiesImpl();
	}

	public void execute(int choice) {
		if (factories.containsKey(choice)) {
			factories.get(choice).execute(driverActivities);
		} else if (choice == -2) {
			driverActivities.showAllActiveUser();
		}
	}

}