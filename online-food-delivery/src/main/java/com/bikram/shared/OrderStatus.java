package com.bikram.shared;

public enum OrderStatus {
  
	ACCEPTED("accepted"), 
	PREPARING_FOOD("preparing_food"), 
	ON_THE_WAY("on_the_way"), 
	DELIVERD("delivered");
	private String orderStatus;
	
	private OrderStatus(String status) {
		orderStatus = status;
	}
	
	public String toString(){
		return orderStatus;
	}	
	
}
