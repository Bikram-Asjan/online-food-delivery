package com.bikram.ui.model.response;


import com.bikram.shared.OrderStatus;

public class OrderResponse {

	private long orderId;

	private String customerContact;

	private int totalPrice;

	private long expectedDeliveryTime;
	
    private String orderStatus;

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}

	public void setExpectedDeliveryTime(long expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
