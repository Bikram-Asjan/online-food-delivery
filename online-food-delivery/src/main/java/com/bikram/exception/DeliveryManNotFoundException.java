package com.bikram.exception;

public class DeliveryManNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1374693115758698868L;

	public DeliveryManNotFoundException(){}
	
	public DeliveryManNotFoundException(String errorMessage){
		super(errorMessage);
	}
	
	public DeliveryManNotFoundException(String errorMessage, Throwable cause){
		super(errorMessage, cause);
	}
}
