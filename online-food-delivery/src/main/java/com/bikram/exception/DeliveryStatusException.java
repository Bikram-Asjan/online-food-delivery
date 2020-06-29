package com.bikram.exception;

public class DeliveryStatusException extends Exception {

	public DeliveryStatusException() {}
	
	public DeliveryStatusException(String errorMessage){
		super(errorMessage);
	}
}
