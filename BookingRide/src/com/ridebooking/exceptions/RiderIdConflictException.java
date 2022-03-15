package com.ridebooking.exceptions;

@SuppressWarnings("serial")
public class RiderIdConflictException extends Exception {
	public RiderIdConflictException(String errMsg) {
		super(errMsg);
	}
}
