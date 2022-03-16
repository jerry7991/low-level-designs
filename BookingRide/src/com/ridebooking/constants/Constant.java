package com.ridebooking.constants;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Constant {
	private Constant() {
	}

	public static final int AMT_PER_KM = 20;
	public static int id = 1;
	public static BufferedReader reader;// = new BufferedReader(new InputStreamReader(System.in));

	public static void init() throws FileNotFoundException {
		reader = new BufferedReader(
				new FileReader("C:\\Users\\Anupkumar_Singh\\git\\low-level-design\\BookingRide\\resoruces\\input.txt"));
	}
}
