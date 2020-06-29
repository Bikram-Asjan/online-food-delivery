package com.bikram.ui.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressInfo {

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	
}
