package com.bikram.shared.dto;

public class OrderDeliveryDto {
   
	private long orderDeliveryId;
	
	private long orderId;
	
	private long deliveryPersonId;
	
	private long orderTime;
	
	private long expectedDeliveryTime;
	
	private String orderStatus;
	
	private String customerContact;
	
	private String deliveryPerosnStatus;

	public String getDeliveryPerosnStatus() {
		return deliveryPerosnStatus;
	}

	public void setDeliveryPerosnStatus(String deliveryPerosnStatus) {
		this.deliveryPerosnStatus = deliveryPerosnStatus;
	}

	public long getOrderDeliveryId() {
		return orderDeliveryId;
	}

	public void setOrderDeliveryId(long orderDeliveryId) {
		this.orderDeliveryId = orderDeliveryId;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	@Override
	public String toString() {
		return "OrderDeliveryDto [orderDeliveryId=" + orderDeliveryId + ", orderId=" + orderId + ", deliveryPersonId="
				+ deliveryPersonId + ", orderTime=" + orderTime + ", expectedDeliveryTime=" + expectedDeliveryTime
				+ ", orderStatus=" + orderStatus + "]";
	}
	
}
