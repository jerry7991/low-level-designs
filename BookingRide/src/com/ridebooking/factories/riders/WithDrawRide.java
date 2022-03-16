package com.ridebooking.factories.riders;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.api.RideFactory;
import com.ridebooking.api.RiderActivity;
import com.ridebooking.constants.Constant;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.RiderIdConflictException;

public class WithDrawRide implements RideFactory {

	BufferedReader reader;

	public WithDrawRide() {
		reader = Constant.reader;
	}

	@Override
	public void execute(RiderActivity riderAcitvity) {
		int id;
		try {
			System.out.println("******Give id for withdraw the ride. ******");
			id = Integer.parseInt(reader.readLine());
			riderAcitvity.withdrawRide(id);
		} catch (NumberFormatException | IOException | BadInputException | RiderIdConflictException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}

}
