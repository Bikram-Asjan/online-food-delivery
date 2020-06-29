package com.bikram.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "order_delivery")
public class OrderDeliveryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long orderDeliveryId;
	
	@Column(name = "order_id", nullable = false)
	private long orderId;
	
	@Column(name = "delivery_person_id", nullable = false)
	private long deliveryPersonId;
	
	@Column(name = "order_time", nullable = false)
	private long orderTime;
	
	@Column(name = "expected_delivery_time", nullable = false)
	private long expectedDeliveryTime;
	
	@Column(name = "customer_contact", nullable = false)
	private String customerContact;
	
	@Column(name = "order_status", nullable = false)
	private String orderStatus;
	

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
		return "OrderDeliveryEntity [orderDeliveryId=" + orderDeliveryId + ", orderId=" + orderId
				+ ", deliveryPersonId=" + deliveryPersonId + ", orderTime=" + orderTime + ", expectedDeliveryTime="
				+ expectedDeliveryTime + ", orderStatus=" + orderStatus + "]";
	}

}
