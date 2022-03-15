package com.ridebooking.factories;

import java.util.HashMap;
import java.util.Map;

import com.ridebooking.api.RiderActivity;
import com.ridebooking.service.RiderActivityImpl;

public class RiderFactoryHandler {
	Map<Integer, RideFactory> factories;
	RiderActivity riderActivity;

	public RiderFactoryHandler(String name) {
		factories = new HashMap<>();
		factories.put(1, new NewRideCreation());
		factories.put(2, new RideUpdate());
		factories.put(3, new WithDrawRide());
		factories.put(4, new CloseRides());
		riderActivity = new RiderActivityImpl(name);
	}

	public void execute(int choice) {
		if (factories.containsKey(choice)) {
			factories.get(choice).execute(riderActivity);
		} else {
			riderActivity.showAllActiveUser();
		}
	}

}
