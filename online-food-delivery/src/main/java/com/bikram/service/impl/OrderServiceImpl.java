package com.bikram.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bikram.exception.DeliveryManNotFoundException;
import com.bikram.exception.DeliveryManRetrievalException;
import com.bikram.exception.DeliveryStatusException;
import com.bikram.exception.OrderDelegacyException;
import com.bikram.exception.OrderNotFoundException;
import com.bikram.exception.OrderPlacedException;
import com.bikram.exception.ShipperException;
import com.bikram.exception.ShipperStateFetchException;
import com.bikram.exception.UpdateOrderStatusException;
import com.bikram.io.entity.DeliveryPersonEntity;
import com.bikram.io.entity.OrderDeliveryEntity;
import com.bikram.io.entity.OrderEntity;
import com.bikram.repo.DeliveryPersonRepository;
import com.bikram.repo.OrderDeliveryRepository;
import com.bikram.repo.OrderRepository;
import com.bikram.service.OrderService;
import com.bikram.shared.DeliveryBoyStatus;
import com.bikram.shared.OrderStatus;
import com.bikram.shared.dto.DeliveryPersonDto;
import com.bikram.shared.dto.DeliveryPersonStateDto;
import com.bikram.shared.dto.DeliveryStatusDto;
import com.bikram.shared.dto.OrderDeliveryDto;
import com.bikram.shared.dto.OrderDto;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDeliveryRepository odRepository;

	@Autowired
	DeliveryPersonRepository deliveryPersonRepo;

	@Override
	public OrderDto createOrder(OrderDto order) throws OrderPlacedException {

		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setRestaurantId(order.getRestaurantId());
		orderEntity.setSpecialNote(order.getSpecialNote());
		orderEntity.setCustomerContact(order.getCustomerContact());
		orderEntity.setTotalPrice(order.getTotalPrice());
		orderEntity.setOrderTime(order.getOrderTime());
		orderEntity.setExpectedDeliveryTime(order.getExpectedDeliveryTime());
		orderEntity.setOrderStatus(OrderStatus.ACCEPTED.toString());
		try {

			OrderEntity resultEntity = orderRepository.save(orderEntity);
			order.setOrderId(resultEntity.getOrderId());
			order.setOrderStatus(orderEntity.getOrderStatus());

			return order;
		} catch (Exception ex) {
			System.out.println("OrderEntity : " + orderEntity);
			throw new OrderPlacedException("Order is not placed");
		}
	}

	@Override
	public OrderDeliveryDto orderDelegate(OrderDeliveryDto deliveryDto)
        throws OrderDelegacyException {

		OrderDeliveryEntity deliveryEntity = new OrderDeliveryEntity();

		try {

			Optional<DeliveryPersonEntity> shipperDtoOpt = 
			    deliveryPersonRepo.findById(deliveryDto.getDeliveryPersonId());
			if(shipperDtoOpt.isPresent()){
				if(shipperDtoOpt.get().getPersonStatus()
				    .equalsIgnoreCase(DeliveryBoyStatus.ENGAGED.toString())){
					throw new OrderDelegacyException("Delivery boy is already busy, "
							+ "please looks for other shippers");
				}
			}
			OrderDto orderDto = this.getOrderDetails(deliveryDto.getOrderId());
			
			if(orderDto.getOrderStatus().equalsIgnoreCase(OrderStatus.DELIVERD.toString())){	
				throw new OrderDelegacyException("order is already delivered");
			}
			deliveryEntity.setOrderId(orderDto.getOrderId());
			deliveryEntity.setDeliveryPersonId(deliveryDto.getDeliveryPersonId());
			deliveryEntity.setOrderTime(orderDto.getOrderTime());
			deliveryEntity.setExpectedDeliveryTime(orderDto.getExpectedDeliveryTime());
			deliveryEntity.setOrderStatus(orderDto.getOrderStatus());
			deliveryEntity.setCustomerContact(orderDto.getCustomerContact());
			
			System.out.println("order id : "+deliveryEntity.getOrderId());
			OrderDeliveryEntity odEntity = odRepository.save(deliveryEntity);

			if (odEntity != null) {

				deliveryDto.setOrderDeliveryId(odEntity.getOrderDeliveryId());
				deliveryDto.setOrderTime(odEntity.getOrderTime());
				deliveryDto.setExpectedDeliveryTime(odEntity.getExpectedDeliveryTime());
				deliveryDto.setCustomerContact(odEntity.getCustomerContact());
				deliveryDto.setOrderStatus(odEntity.getOrderStatus());
			}

			DeliveryPersonDto deliveryPersonDto = this.updateDeliveryBoyStatus(
			    deliveryDto.getDeliveryPersonId(), DeliveryBoyStatus.ENGAGED.toString());
			
			if(deliveryPersonDto.getPersonStatus() != null){
				deliveryDto.setDeliveryPerosnStatus(deliveryPersonDto.getPersonStatus());
			}

			return deliveryDto;
		} catch (Exception ex) {
			
		    throw new OrderDelegacyException("Order delegation failed  "+ex.getMessage());
		}
	}

	@Override
	public OrderDto getOrderDetails(long orderId) throws OrderNotFoundException {

		try {
			Optional<OrderEntity> orderEntityOptional = orderRepository.findById(orderId);
			
			OrderDto orderDto = new OrderDto();

			if (orderEntityOptional.isPresent()) {
				OrderEntity orderEntity = orderEntityOptional.get();
				System.out.println("ordderEntity : "+orderEntity.toString());
				orderDto.setOrderId(orderEntity.getOrderId());
				orderDto.setCustomerContact(orderEntity.getCustomerContact());
				orderDto.setExpectedDeliveryTime(orderEntity.getExpectedDeliveryTime());
				orderDto.setOrderTime(orderEntity.getOrderTime());
				orderDto.setOrderStatus(orderEntity.getOrderStatus());
				orderDto.setCustomerContact(orderEntity.getCustomerContact());
			}
			return orderDto;

		} catch (Exception e) {
			throw new OrderNotFoundException("order not found with id : " + orderId);
		}

	}

	@Override
	public DeliveryPersonDto updateDeliveryBoyStatus(long deliveryPersonId, String personStatus)
			throws DeliveryStatusException {

		DeliveryPersonDto deliveryboyDto = new DeliveryPersonDto();

		try {
			Optional<DeliveryPersonEntity> personEntityOptional = deliveryPersonRepo.findById(deliveryPersonId);
			if (personEntityOptional.isPresent()) {
				DeliveryPersonEntity dpEntity = personEntityOptional.get();
				dpEntity.setPersonStatus(personStatus);
                
				DeliveryPersonEntity updatedPersonEntity = deliveryPersonRepo.save(dpEntity);
				deliveryboyDto.setContact(updatedPersonEntity.getContact());
				deliveryboyDto.setPersonStatus(updatedPersonEntity.getPersonStatus());
				deliveryboyDto.setDeliveryPersonId(deliveryPersonId);

			}
			return deliveryboyDto;
		} catch (Exception e) {
			throw new DeliveryStatusException("Failed to update delivery person status");
		}
	}

	@Override
	public String updateDeliveryStatus(DeliveryStatusDto deliveryStatusDto)
        throws UpdateOrderStatusException {
		
		String updatedStatResult = "";
		try {
		OrderDeliveryEntity odEntity = odRepository.findByOrderId(
		    deliveryStatusDto.getOrderId());
		odEntity.setOrderStatus(deliveryStatusDto.getOrderStatus());
		odRepository.save(odEntity);
		
		Optional<OrderEntity> orderEntityOpt = orderRepository.findById(
		    deliveryStatusDto.getOrderId());
        
		if(orderEntityOpt.isPresent()){
			OrderEntity orderEntity = orderEntityOpt.get();
			orderEntity.setOrderStatus(deliveryStatusDto.getOrderStatus());
			orderRepository.save(orderEntity);
			updatedStatResult = orderEntity.getOrderStatus();
		}
		
		
		if(deliveryStatusDto.getOrderStatus().equalsIgnoreCase(
		    OrderStatus.DELIVERD.toString())){
		  long deliveryPersonId = odEntity.getDeliveryPersonId();
		  Optional<DeliveryPersonEntity> dpEntityOpt = 
              deliveryPersonRepo.findById(deliveryPersonId);
			
		  if(dpEntityOpt.isPresent()){
	        DeliveryPersonEntity dpEntity = dpEntityOpt.get();
			System.out.println("person entity : "+dpEntity.toString());
			dpEntity.setPersonStatus(DeliveryBoyStatus.ACTIVE.toString());
			    deliveryPersonRepo.save(dpEntity);
			}
		}
		 return updatedStatResult;
		} catch (Exception ex){
				
			throw new UpdateOrderStatusException(
			    "Failed to update order status "+ex.getMessage());
		}
	}

	@Override
	public List<DeliveryPersonDto> getActiveDeliveryPersons() 
        throws DeliveryManRetrievalException {
		
		
		try{
			List<DeliveryPersonEntity> activeDeliveryPersons = 
					deliveryPersonRepo.findAllActiveDeliveryPersons();
			System.out.println("active delivery persons : "+activeDeliveryPersons);
				List<DeliveryPersonDto> activePersons = new ArrayList<>();
				
				for(DeliveryPersonEntity personEntity : activeDeliveryPersons){
				    DeliveryPersonDto personDto = new DeliveryPersonDto();
				    personDto.setDeliveryPersonId(personEntity.getDeliveryPersonId());
				    personDto.setContact(personEntity.getContact());
				    personDto.setPersonStatus(personEntity.getPersonStatus());
				    activePersons.add(personDto);
				}
				return activePersons;
			
		} catch (Exception ex){
			throw new DeliveryManRetrievalException(
			    "Unable to fetch all active delivery man");
		}
		
	}

	@Override
	public DeliveryPersonStateDto getDeliveryPersonState(long deliveryPersonId)
       throws ShipperException  {
		
		try{
			Optional<DeliveryPersonEntity> shipperOptional = 
			    deliveryPersonRepo.findById(deliveryPersonId);
			DeliveryPersonStateDto shipperStateDto = new DeliveryPersonStateDto();
			if(shipperOptional.isPresent()){
						
			  DeliveryPersonEntity shipper = shipperOptional.get();
			  if(shipper.getPersonStatus().equalsIgnoreCase(
			      DeliveryBoyStatus.ENGAGED.toString())){
							
			    OrderDeliveryEntity odEntity = 
				    this.findByShipperIdAndStatusNotDelivered(deliveryPersonId); 
					            
				System.out.println("odentity find by status not delivered : "+(odEntity != null));
				if(odEntity != null){
								
  				  shipperStateDto.setOrderId(odEntity.getOrderId());
	 			  shipperStateDto.setPersonStatus(shipper.getPersonStatus());
	  			  shipperStateDto.setContact(shipper.getContact());
	  			  shipperStateDto.setOrderTime(odEntity.getOrderTime());
				  shipperStateDto.setEpectedDeliveryTime(odEntity.getExpectedDeliveryTime());
				  shipperStateDto.setDeliveryPersonId(shipper.getDeliveryPersonId());
				  
				  System.out.println("system time : "+System.currentTimeMillis());
				  System.out.println("expected delivery time : "+odEntity.getExpectedDeliveryTime());
				  System.out.println("time check : "+(System.currentTimeMillis() > odEntity.getExpectedDeliveryTime()));
				  
				  if(System.currentTimeMillis() > odEntity.getExpectedDeliveryTime()){
					  
				    shipperStateDto.setMessage("Delivery will take more than usual time");
				    
				  } else {
					long timeLeftToDeliverInMillis = 
			            odEntity.getExpectedDeliveryTime() - System.currentTimeMillis();
						String timeLeftToDeliver = String.format("%02d:%02d:%02d", 
					        TimeUnit.MILLISECONDS.toHours(timeLeftToDeliverInMillis),
					        TimeUnit.MILLISECONDS.toMinutes(timeLeftToDeliverInMillis) - TimeUnit.HOURS.toMinutes(
					        TimeUnit.MILLISECONDS.toHours(timeLeftToDeliverInMillis)),
					        TimeUnit.MILLISECONDS.toSeconds(timeLeftToDeliverInMillis) - TimeUnit.MINUTES.toSeconds(
 				            TimeUnit.MILLISECONDS.toMinutes(timeLeftToDeliverInMillis)));
	                        
						shipperStateDto.setTimeLeftToDeliver(timeLeftToDeliver);
								      
				     }
			       }
		        } else {
		        	shipperStateDto.setDeliveryPersonId(shipper.getDeliveryPersonId());
		        	shipperStateDto.setPersonStatus(shipper.getPersonStatus());
		        }		
	        }
	    return shipperStateDto;
	
		} catch(Exception ex ){
			throw new ShipperException(ex.getMessage());
		}     
	}
	
	public OrderDeliveryEntity findByShipperIdAndStatusNotDelivered(long deliveryPersonId)
	    throws ShipperStateFetchException {
		try{
		OrderDeliveryEntity odEntity = 
	            odRepository.findByShipperIdAndStatusNotDelivered(deliveryPersonId);
		return odEntity;
		
		} catch (Exception ex){
			throw new ShipperStateFetchException("Failed to retrieve order delivery data");
		}
	}
	
}
