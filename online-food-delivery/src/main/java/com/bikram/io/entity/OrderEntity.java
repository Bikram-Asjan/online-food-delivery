package com.bikram.io.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "food_order")
public class OrderEntity {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;
	
	@Column(name = "restaurant_id")
    private String restaurantId;
	
	@Column(name = "special_note")
	private String specialNote;

	@Column(name = "customer_contact")
	private String customerContact;
	
	@Column(name = "total_price")
	private int totalPrice;

	@Column(name = "order_time")
	private long orderTime;
	
	@Column(name = "expected_delivery_time")
	private long expectedDeliveryTime;
	
	@Column(name = "order_status")
	private String orderStatus;

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
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

	public void setExpectedDeliveryTime(long expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + orderId + ", restaurantId=" + restaurantId + ", specialNote=" + specialNote
				+ ", customerContact=" + customerContact + ", totalPrice=" + totalPrice + ", orderTime=" + orderTime
				+ ", expectedDeliveryTime=" + expectedDeliveryTime + ", orderStatus=" + orderStatus + "]";
	}
	
	
}
