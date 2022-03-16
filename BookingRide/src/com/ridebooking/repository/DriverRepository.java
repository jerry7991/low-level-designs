package com.ridebooking.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ridebooking.entity.Driver;
import com.ridebooking.enums.DriverStatus;

// Let's make repository as singleton show that it'll remain same for rider perspective and also for driver perspective
public class DriverRepository {

	private Map<Integer, Driver> activeDriver;
	private static DriverRepository driverRepository;

	private DriverRepository() {
		activeDriver = new HashMap<>();
	}

	public static DriverRepository getInstance() {
		if (driverRepository == null) {
			driverRepository = new DriverRepository();
		}
		return driverRepository;
	}

	public void addDriver(Driver driver) {
		this.activeDriver.put(driver.getId(), driver);
	}

	public boolean existActiveDriverByid(int id) {
		return activeDriver.containsKey(id);
	}

	public Driver getDriverById(int id) {
		return activeDriver.get(id);
	}

	public void deleteById(int id) {
		if (activeDriver.containsKey(id)) {
			activeDriver.remove(id);
		}
	}

	public void makeUnavailable(int id) {
		activeDriver.get(id).setDriverStatus(DriverStatus.NOT_AVAILABLE);
	}

	public void markCompletedDrive(int id) {
		Driver driver = activeDriver.get(id);
		driver.setDriverStatus(DriverStatus.IDLE);
		driver.setCurrSeat(0);
	}

	public boolean existByid(int id) {
		return activeDriver.containsKey(id);
	}

	public void updateSeatById(int driverId, int seat) {
		Driver driver = activeDriver.get(driverId);
		driver.setCurrSeat(seat + driver.getCurrSeat());
	}

	public void showAllDriver() {
		System.out.println("------------------------------------");
		System.out.println("\n ******* Active Drivers **** \n");
		for (Entry<Integer, Driver> entry : activeDriver.entrySet()) {
			System.out.println("Id : " + entry.getKey() + " Details => " + entry.getValue());
		}
	}
}
