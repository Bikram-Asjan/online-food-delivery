package com.bikram.exception;

public class OrderNotFoundException extends Exception {

	private static final long serialVersionUID = 1663587849285465829L;

	public OrderNotFoundException() {}
	
	public OrderNotFoundException(String message){
		super(message);
	}
	
	public OrderNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
}
