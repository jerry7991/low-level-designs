package com.ridebooking.exceptions;

@SuppressWarnings("serial")
public class MaxSheetExceedException extends Exception {
	public MaxSheetExceedException(String errMsg) {
		super(errMsg);
	}
}
