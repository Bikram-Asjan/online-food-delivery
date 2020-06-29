package com.bikram.ui.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL )
public class OrderDeliveryReq {
	
	private long orderId;
	
	private long deliveryPersonId;

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

	@Override
	public String toString() {
		return "OrderDeliveryReq [orderId=" + orderId + ", deliveryPersonId=" + deliveryPersonId + "]";
	}

}
