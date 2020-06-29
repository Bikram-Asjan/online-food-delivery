package com.bikram.shared;

/**
 * To check the order status is valid or not, orederStatus can be 
 * any of these ACCEPTED, PREPARING_FOOD, ON_THE_WAY, DELIVERED 
 * @author Bikram
 *
 */
public class Utils {
	
	public static boolean isValidOrderStatus(String test){
		
		for(OrderStatus orderStatus : OrderStatus.values()){	
			System.out.println("order status name : "+orderStatus.toString());
			if(orderStatus.toString().equalsIgnoreCase(test.trim())){
				return true;
			}
		}
		return false;
	}
}
