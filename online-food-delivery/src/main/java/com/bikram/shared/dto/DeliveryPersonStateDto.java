package com.bikram.shared.dto;

public class DeliveryPersonStateDto {
	
	private long deliveryPersonId;
	private String personStatus;
	private long orderId;
	private long epectedDeliveryTime;
	private long orderTime;
	private String timeLeftToDeliver;
	private String contact;
	
	// The field will be used if delivery takes more than usual time
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public long getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}
	public long getDeliveryPersonId() {
		return deliveryPersonId;
	}
	public void setDeliveryPersonId(long deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}
	public String getPersonStatus() {
		return personStatus;
	}
	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getEpectedDeliveryTime() {
		return epectedDeliveryTime;
	}
	public void setEpectedDeliveryTime(long epectedDeliveryTime) {
		this.epectedDeliveryTime = epectedDeliveryTime;
	}
	
	public String getTimeLeftToDeliver() {
		return timeLeftToDeliver;
	}
	public void setTimeLeftToDeliver(String timeLeftToDeliver) {
		this.timeLeftToDeliver = timeLeftToDeliver;
	}
	@Override
	public String toString() {
		return "DeliveryPersonStateDto [deliveryPersonId=" + deliveryPersonId + ", personStatus=" + personStatus
				+ ", orderId=" + orderId + ", epectedDeliveryTime=" + epectedDeliveryTime + ", orderTime=" + orderTime
				+ ", timeLeftToDeliver=" + timeLeftToDeliver + ", contact=" + contact + "]";
	}
	
}
