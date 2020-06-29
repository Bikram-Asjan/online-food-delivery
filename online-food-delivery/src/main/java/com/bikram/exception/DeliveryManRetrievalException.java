package com.bikram.exception;

public class DeliveryManRetrievalException extends Exception {

	public DeliveryManRetrievalException(){}
	
	public DeliveryManRetrievalException(String errorMessage){
		super(errorMessage);
	}
	
	public DeliveryManRetrievalException(String errorMessage, Throwable cause){
		super(errorMessage, cause);
	}
}
