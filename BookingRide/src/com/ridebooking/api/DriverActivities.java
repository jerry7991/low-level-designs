package com.ridebooking.api;

import com.ridebooking.entity.Driver;
import com.ridebooking.exceptions.BadInputException;
import com.ridebooking.exceptions.DriverAlreadyExistException;
import com.ridebooking.exceptions.InCompleteTaskException;
import com.ridebooking.exceptions.ResourceNotFoundException;

public interface DriverActivities {
	void addDriver(Driver driver) throws DriverAlreadyExistException, BadInputException;

	void makeUnavailableToDriver(int id) throws ResourceNotFoundException, InCompleteTaskException;

	void removeDriver(int id) throws ResourceNotFoundException;

	void markCompleteDrive(int id) throws ResourceNotFoundException;

	void showAllActiveUser();
}