package com.bikram.io.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_person")
public class DeliveryPersonEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long deliveryPersonId;

	@Column(name = "delivery_person_contact")
	private String contact;

	@Column(name = "person_status")
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
		return "DeliveryPersonEntity [deliveryPersonId=" + deliveryPersonId + ", contact=" + contact + ", personStatus="
				+ personStatus + "]";
	}

}
