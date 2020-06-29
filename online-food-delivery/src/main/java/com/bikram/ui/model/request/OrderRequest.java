package com.bikram.ui.model.request;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.bikram.ui.model.AddressInfo;
import com.bikram.ui.model.ItemQuantity;
import com.bikram.ui.model.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@JsonInclude(JsonInclude.Include.NON_DEFAULT)
//@Data
//@Getter @Setter
public class OrderRequest {

	
    private String restaurantId;
  
    private List<ItemQuantity> items;
  
    private String specialNote;
    
    private String customerContact;

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
    
}
