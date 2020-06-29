package com.bikram.ui.model.response;

public class OrderDeliveryResponse {

	private long orderId;
	
	private long deliveryPersonId;
	
	private String customerContact;
	
	private String orderStatus;
	
	private String acceptResult;
	
	private String deliveryPersonStatus;

	public String getDeliveryPersonStatus() {
		return deliveryPersonStatus;
	}

	public void setDeliveryPersonStatus(String deliveryPersonStatus) {
		this.deliveryPersonStatus = deliveryPersonStatus;
	}

	public String getAcceptResult() {
		return acceptResult;
	}

	public void setAcceptResult(String acceptResult) {
		this.acceptResult = acceptResult;
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

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
}
