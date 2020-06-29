package com.bikram.exception;

public class OrderDelegacyException extends Exception {
	
	public OrderDelegacyException(){}
	
	public OrderDelegacyException(String errorMessage){
		super(errorMessage);
	}
	
	public OrderDelegacyException(String errooMessage, Throwable cause){
		super(errooMessage, cause);
	}

}
