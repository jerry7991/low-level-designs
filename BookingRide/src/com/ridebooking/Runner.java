package com.ridebooking;

import java.io.BufferedReader;
import java.io.IOException;

import com.ridebooking.constants.Constant;
import com.ridebooking.factories.RiderFactoryHandler;

public class Runner {
	private static void info() {
		System.out.println("1. For create new ride.");
		System.out.println("2. Update Ride Running ride.");
		System.out.println("3. Withdraw ride.");
		System.out.println("4. Close ride.");
		System.out.println("-1. Exit");
	}

	public static void main(String[] args) {
		BufferedReader reader = Constant.reader;
		try {
			System.out.println("********* Enter Driver Name ********");
			RiderFactoryHandler factory = new RiderFactoryHandler(reader.readLine());
			info();
			int choice = Integer.parseInt(reader.readLine());
			while (choice != -1) {
				factory.execute(choice);
				System.out.println("---------------Enter Choice----------------");
				choice = Integer.parseInt(reader.readLine());
			}
		} catch (IOException e) {
			System.out.println("Error :: " + e.getMessage());
		}
	}
}
