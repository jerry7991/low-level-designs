package com.ridebooking.service;

import com.ridebooking.api.DriverActivities;
import com.ridebooking.entity.Driver;
import com.ridebooking.enums.DriverStatus;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.DriverAlreadyExistException;
import com.ridebooking.exceptions.InCompleteTaskException;
import com.ridebooking.exceptions.ResourceNotFoundException;
import com.ridebooking.repository.DriverRepository;

public class DriverActivitiesImpl implements DriverActivities {

	DriverRepository driverRepository;

	public DriverActivitiesImpl() {
		driverRepository = DriverRepository.getInstance();
	}

	@Override
	public void addDriver(Driver driver) throws DriverAlreadyExistException, BadInputException {
		if (driverRepository.existActiveDriverByid(driver.getId())) {
			throw new DriverAlreadyExistException("Driver id = " + driver.getId() + " already exist. Change id.");
		}

		if (driver.getMaxSeat() == 0) {
			throw new BadInputException("0 seat will not be able to carry a rider.");
		}
		driverRepository.addDriver(driver);
	}

	@Override
	public void makeUnavailableToDriver(int id) throws ResourceNotFoundException, InCompleteTaskException {
		if (!driverRepository.existActiveDriverByid(id)) {
			throw new ResourceNotFoundException("Given driver id = " + id + " doesn't exist.");
		}
		Driver driver = driverRepository.getDriverById(id);
		if (driver.getDriverStatus() == DriverStatus.BOOKED) {
			throw new InCompleteTaskException(
					"Driver shouldn't any active drive for become un-available. Please complete the drive first");
		}
		driverRepository.makeUnavailable(id);
	}

	@Override
	public void removeDriver(int id) throws ResourceNotFoundException {
		if (!driverRepository.existByid(id)) {
			throw new ResourceNotFoundException("Given driver id = " + id + " doesn't exist.");
		}
		driverRepository.deleteById(id);
	}

	@Override
	public void markCompleteDrive(int id) throws ResourceNotFoundException {
		if (!driverRepository.existActiveDriverByid(id)) {
			throw new ResourceNotFoundException("Given driver id = " + id + " doesn't exist.");
		}
		driverRepository.markCompletedDrive(id);
	}

	@Override
	public void showAllActiveUser() {
		driverRepository.showAllDriver();
	}
}
