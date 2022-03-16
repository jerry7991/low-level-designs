package com.ridebooking.factories.riders;

import java.util.HashMap;
import java.util.Map;

import com.ridebooking.api.RideFactory;
import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.service.RiderActivityImpl;

public class RiderFactoryHandler {
	Map<Integer, RideFactory> factories;
	RiderActivity riderActivity;

	public RiderFactoryHandler() {
		factories = new HashMap<>();
		factories.put(Constant.id++, new NewRideCreation());
		factories.put(Constant.id++, new RideUpdate());
		factories.put(Constant.id++, new WithDrawRide());
		factories.put(Constant.id++, new CloseRides());
		riderActivity = new RiderActivityImpl();
	}

	public void execute(int choice) {
		if (factories.containsKey(choice)) {
			factories.get(choice).execute(riderActivity);
		} else if (choice == -2) {
			riderActivity.showAllActiveUser();
		}
	}

}