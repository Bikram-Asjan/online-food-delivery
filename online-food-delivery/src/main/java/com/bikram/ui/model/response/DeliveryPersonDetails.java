package com.bikram.ui.model.response;

public class DeliveryPersonDetails {
	
	 public DeliveryPersonDetails(long deliveryPersonId, String contact, String personStatus) {
		super();
		this.deliveryPersonId = deliveryPersonId;
		this.contact = contact;
		this.personStatus = personStatus;
	}

	private long deliveryPersonId;
		
	 private String contact;
		
	 private String personStatus;

	public long getDeliveryPersonId() {
		return deliveryPersonId;
	}

	public void setDeliveryPersonId(long deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	@Override
	public String toString() {
		return "DeliveryPersonDetails [deliveryPersonId=" + deliveryPersonId + ", contact=" + contact
				+ ", personStatus=" + personStatus + "]";
	}

}
