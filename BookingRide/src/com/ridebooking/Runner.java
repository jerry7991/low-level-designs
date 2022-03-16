package com.ridebooking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.ridebooking.api.BookingRideSystem;
import com.ridebooking.constants.Constant;
import com.ridebooking.factories.BookingRideSystemImpl;

public class Runner {
	private static void info() {
		System.out.println("1. For create new ride.");
		System.out.println("2. Update Ride Running ride.");
		System.out.println("3. Withdraw ride.");
		System.out.println("4. Close ride.");
		System.out.println("5. Add New Driver.");
		System.out.println("6. Make Unavailable to a driver.");
		System.out.println("7. Remove the driver.");
		System.out.println("8. Complete the drive for driver.");
		System.out.println("-1. Exit");
	}

	public static void main(String[] args) throws FileNotFoundException {
		Constant.init();
		BufferedReader reader = Constant.reader;
		try {
			System.out.println("********* Enter Driver Name ********");
			BookingRideSystem bookingRideSystem = new BookingRideSystemImpl();
			info();
			int choice = 0;
			while (choice != -1) {
				System.out.println("---------------Enter your identity (drive/rider) and Choice----------------");
				String[] input = reader.readLine().split(" ");
				choice = Integer.parseInt(input[1]);
				if (choice == -1)
					break;
				bookingRideSystem.execute(input[0], choice);
			}
		} catch (IOException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}
}
