package com.bikram.service;

import java.util.List;

import com.bikram.exception.DeliveryManNotFoundException;
import com.bikram.exception.DeliveryManRetrievalException;
import com.bikram.exception.DeliveryStatusException;
import com.bikram.exception.OrderDelegacyException;
import com.bikram.exception.OrderNotFoundException;
import com.bikram.exception.OrderPlacedException;
import com.bikram.exception.ShipperException;
import com.bikram.exception.UpdateOrderStatusException;
import com.bikram.shared.dto.DeliveryPersonDto;
import com.bikram.shared.dto.DeliveryPersonStateDto;
import com.bikram.shared.dto.DeliveryStatusDto;
import com.bikram.shared.dto.OrderDeliveryDto;
import com.bikram.shared.dto.OrderDto;

public interface OrderService {

	
   OrderDto createOrder(OrderDto order) throws OrderPlacedException;
   
   OrderDeliveryDto orderDelegate(OrderDeliveryDto deliveryDto) 
       throws OrderDelegacyException;
   
   OrderDto getOrderDetails(long orderId) throws OrderNotFoundException;
   
   DeliveryPersonDto updateDeliveryBoyStatus(long deliveryPersonId, 
       String personStatus) throws DeliveryStatusException; 
   
   String updateDeliveryStatus(DeliveryStatusDto deliveryStatusDto) 
       throws UpdateOrderStatusException;
   
   List<DeliveryPersonDto> getActiveDeliveryPersons() 
       throws DeliveryManRetrievalException; 
   
   DeliveryPersonStateDto getDeliveryPersonState(long deliveryPersonId)
       throws ShipperException;
   
}
