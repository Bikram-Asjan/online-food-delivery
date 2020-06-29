package com.bikram.exception;

public class ShipperException extends Exception {

	public ShipperException(){}
	public ShipperException(String errorMessage){ super(errorMessage);}
	
	public ShipperException(String errorMessage, Throwable cause){super(errorMessage, cause);}
}
