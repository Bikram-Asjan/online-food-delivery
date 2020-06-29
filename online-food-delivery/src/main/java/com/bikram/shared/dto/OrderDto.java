package com.bikram.shared.dto;

import java.util.List;

import com.bikram.ui.model.ItemQuantity;

public class OrderDto {

	private long orderId;
	
	private String restaurantId;
	
	private List<ItemQuantity> items;

	private String specialNote;

	private String customerContact;
	
	private int totalPrice;

	private long orderTime;
	
	private long expectedDeliveryTime;

	private String orderStatus;
	
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public List<ItemQuantity> getItems() {
		return items;
	}

	public void setItems(List<ItemQuantity> items) {
		this.items = items;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
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

	public void setExpectedDeliveryTime(long deliveryTime) {
		this.expectedDeliveryTime = deliveryTime;
	}

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

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

	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", restaurantId=" + restaurantId + ", items=" + items + ", specialNote="
				+ specialNote + ", customerContact=" + customerContact + ", totalPrice=" + totalPrice + ", orderTime="
				+ orderTime + ", expectedDeliveryTime=" + expectedDeliveryTime + "]";
	}
	
   
}
