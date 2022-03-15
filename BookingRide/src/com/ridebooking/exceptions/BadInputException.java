package com.ridebooking.exceptions;

@SuppressWarnings("serial")
public class BadInputException extends Exception {
	public BadInputException(String errMsg) {
		super(errMsg);
	}
}
