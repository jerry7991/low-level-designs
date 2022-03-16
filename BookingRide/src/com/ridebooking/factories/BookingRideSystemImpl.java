package com.ridebooking.factories;

import com.ridebooking.api.BookingRideSystem;
import com.ridebooking.factories.drivers.DriverFactoryHandler;
import com.ridebooking.factories.riders.RiderFactoryHandler;

public class BookingRideSystemImpl implements BookingRideSystem {
	private DriverFactoryHandler driverFactory;
	private RiderFactoryHandler rideFactory;

	public BookingRideSystemImpl() {
		rideFactory = new RiderFactoryHandler();
		driverFactory = new DriverFactoryHandler();
	}

	@Override
	public void execute(String idendtity, int choice) {
		if (idendtity.equals("driver")) {
			driverFactory.execute(choice);
		} else {
			rideFactory.execute(choice);
		}
	}

}
