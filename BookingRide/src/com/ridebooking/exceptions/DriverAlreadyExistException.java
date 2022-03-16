package com.ridebooking.exceptions;

@SuppressWarnings("serial")
public class DriverAlreadyExistException extends Exception {
	public DriverAlreadyExistException(String errMsg) {
		super(errMsg);
	}
}
