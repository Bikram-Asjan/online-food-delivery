package com.bikram.exception;

public class ShipperStateFetchException extends Exception {

	public ShipperStateFetchException(){}
	
	public ShipperStateFetchException(String errorMessage){
		super(errorMessage);
	}
	
	public ShipperStateFetchException(String errorMessage, Throwable cause){
		super(errorMessage, cause);
	}
}
