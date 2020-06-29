package com.bikram.exception;

public class OrderPlacedException extends Exception {
	
	private static final long serialVersionUID = -4445997789366894772L;
	
	private String errorMessage;

	public OrderPlacedException(String errorMessage) {
		super(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
