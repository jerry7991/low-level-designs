package com.ridebooking.exceptions;

@SuppressWarnings("serial")
public class InCompleteTaskException extends Exception {
	public InCompleteTaskException(String errMsg) {
		super(errMsg);
	}
}
