package com.bikram.exception;

public class UpdateOrderStatusException extends Exception{

	public UpdateOrderStatusException(){}
	
	public UpdateOrderStatusException(String errorMessage){
		super(errorMessage);
	}
	
	public UpdateOrderStatusException(String errorMessage, Throwable cause){
	    super(errorMessage, cause);	
	}
}
