package com.bikram.shared.dto;

public class DeliveryStatusDto {
	
	private long orderId;
	private String orderStatus;
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "DeliveryStatusDto [orderId=" + orderId + ", orderStatus=" + orderStatus + "]";
	}

}
