package com.bikram.ui.model.response;

public class DeliveryPersonStateResp {

	private long deliveryPersonId;
	
	private String personStatus;

	private long orderTime;

	private long expectedDeliveryTime;

	private long orderId;

	private String timeLeftToDeliver;

	// this field will be used if delivery takes more than usual time
	private String message;
	
	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	public long getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(long orderTime) {
		this.orderTime = orderTime;
	}

	public long getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}

	public void setExpectedDeliveryTime(long expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getDeliveryPersonId() {
		return deliveryPersonId;
	}

	public void setDeliveryPersonId(long deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}

	public String getTimeLeftToDeliver() {
		return timeLeftToDeliver;
	}

	public void setTimeLeftToDeliver(String timeLeftToDeliver) {
		this.timeLeftToDeliver = timeLeftToDeliver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "DeliveryPersonStateResp [deliveryPersonId=" + deliveryPersonId + ", personStatus=" + personStatus
				+ ", orderTime=" + orderTime + ", expectedDeliveryTime=" + expectedDeliveryTime + ", orderId=" + orderId
				+ ", timeLeftToDeliver=" + timeLeftToDeliver + ", message=" + message + "]";
	}
    
}
