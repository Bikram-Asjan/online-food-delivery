package com.bikram.shared;

public enum DeliveryBoyStatus {

	ACTIVE("active"), 
	ENGAGED("engaged"), 
	INACTIVE("inactive");
	
	private String shipperStatus;

	private DeliveryBoyStatus(String personStatus) {
		this.shipperStatus = personStatus;
	}
	
	public String toString(){
		return shipperStatus;
	}
}
