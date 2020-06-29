package com.bikram.ui.model;



import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserInfo {

	
	private String id;
	
	private String firstName;
	private String lastName;
	private String phone;
	private String address;
	private String emailId;
	
}
