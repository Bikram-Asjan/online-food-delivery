package com.bikram.ui.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bikram.exception.DeliveryManRetrievalException;
import com.bikram.exception.OrderDelegacyException;
import com.bikram.exception.OrderNotFoundException;
import com.bikram.exception.OrderPlacedException;
import com.bikram.exception.ShipperException;
import com.bikram.exception.UpdateOrderStatusException;
import com.bikram.service.OrderService;
import com.bikram.shared.Utils;
import com.bikram.shared.dto.DeliveryPersonDto;
import com.bikram.shared.dto.DeliveryPersonStateDto;
import com.bikram.shared.dto.DeliveryStatusDto;
import com.bikram.shared.dto.OrderDeliveryDto;
import com.bikram.shared.dto.OrderDto;
import com.bikram.ui.model.request.DeliveryStatusModel;
import com.bikram.ui.model.request.OrderDeliveryReq;
import com.bikram.ui.model.request.OrderRequest;
import com.bikram.ui.model.response.DeliveryPersonDetails;
import com.bikram.ui.model.response.DeliveryPersonStateResp;
import com.bikram.ui.model.response.OrderDeliveryResponse;
import com.bikram.ui.model.response.OrderResponse;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class OrderRest {
	
	@Autowired
	OrderService orderService;
	
	
	/**
	 * To place an order, customer will use this API.
	 * After placed an order the status of the order will be ACCEPTED 
     * @param OrderRequest
	   
	 * @return OrderResponse
	 *  order details
	 * @throws OrderPlacedException
	 */
	@RequestMapping(value = "/restaurants/orders", method = RequestMethod.POST)
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest) 
	   throws OrderPlacedException {
		
		
		OrderResponse orderResp = new OrderResponse();
		OrderDto orderResult = null;
		
		if(orderRequest != null){
		OrderDto orderDto = new OrderDto();
		BeanUtils.copyProperties(orderRequest, orderDto);
		orderDto.setOrderTime(System.currentTimeMillis());
		orderDto.setExpectedDeliveryTime(System.currentTimeMillis()+3600000);
	    
		orderDto.setTotalPrice(orderRequest.getItems().stream()
				.mapToInt(item -> item.getPrice() * item.getQuantity()).sum());
		
	    orderResult = orderService.createOrder(orderDto);
		}
		
		if(orderResult != null){
		
		orderResp.setOrderId(orderResult.getOrderId());
	    orderResp.setCustomerContact(orderResult.getCustomerContact());
	    orderResp.setTotalPrice(orderResult.getTotalPrice());
	    orderResp.setExpectedDeliveryTime(orderResult.getExpectedDeliveryTime());
	    orderResp.setOrderStatus(orderResult.getOrderStatus());
	    
		}
		return orderResp;
	}
	
	/**
	 * To delegate an order to a delivery boy who is
	 * active, not engaged, (the status of delivery boy can be active, inactive, engaged).
	 * Once an order in delegated to a delivery boy, the status of that delivery boy will be engaged. 
	 * If the delivery boy is engaged to deliver a parcel then it will throw 
	 * exception saying Delivery boy is already busy, please looks for other shippers
	 * 
     * @param OrderDeliveryReq
	 * @return OrderDeliveryResponse
	 *  order delivery details
	 * @throws OrderDelegacyException
	 */
	@RequestMapping(value = "/orders/delegate", method = RequestMethod.POST)
	public OrderDeliveryResponse orderDelegatetoDelivery(
	    @RequestBody OrderDeliveryReq deliveryReq)
        throws OrderDelegacyException {
		
		OrderDeliveryDto deliveryDto = new OrderDeliveryDto();
		
		if(StringUtils.isEmpty(deliveryReq.getDeliveryPersonId())
            || StringUtils.isEmpty(deliveryReq.getOrderId())) {
			throw new OrderDelegacyException(
		        "order id and delivery person id can't be null");
		}
		deliveryDto.setOrderId(deliveryReq.getOrderId());
		deliveryDto.setDeliveryPersonId(deliveryReq.getDeliveryPersonId());
		
		OrderDeliveryDto odDTO = orderService.orderDelegate(deliveryDto);
		
		OrderDeliveryResponse odResponse = new OrderDeliveryResponse();
		odResponse.setDeliveryPersonId(odDTO.getDeliveryPersonId());
		odResponse.setOrderId(odDTO.getOrderId());
		odResponse.setCustomerContact(odDTO.getCustomerContact());
		odResponse.setOrderStatus(odDTO.getOrderStatus());
		odResponse.setAcceptResult("delivery accepted");
		odResponse.setDeliveryPersonStatus(odDTO.getDeliveryPerosnStatus());
		
		return odResponse;
	}
	
	/**
	 * To update the order status (This API can be used by delivery boy)
	 * orderStatus can be any of these (ACCEPTED, PREPARING_FOOD, ON_THE_WAY, DELIVERED).
	 * Once an order is placed in restaurant, the status of the order will be ACCEPTED.
	 * Delivery Boy can update the status as PREPARING_FOOD.
	 * If delivery boy collects order parcel from Restaurant, he can update this as ON_THE_WAY.
	 * After delivered the parcel to the Customer, delivery boy will update the status as DELIVERED 
	 * and the status of the delivery boy will be active instead of engaged. 
     * @param DeliveryStatusModel, contains two parameters, orderId, orderStatus
	 * @return String
	 *  order status with respect to orderId
	 * @throws UpdateOrderStatusException
	 */
	@RequestMapping(value = "/update/status", method = RequestMethod.POST)
	public String updateOrderDeliveryStatus(
        @RequestBody DeliveryStatusModel deliveryStatusModel) throws UpdateOrderStatusException {
		
		if(StringUtils.isEmpty(deliveryStatusModel.getOrderId()) || 
				StringUtils.isEmpty(deliveryStatusModel.getOrderStatus().trim())){
			throw new UpdateOrderStatusException("OrderId and order status can't be null");
		}
		
		if(!Utils.isValidOrderStatus(deliveryStatusModel.getOrderStatus())){
		   throw new UpdateOrderStatusException("order status is invalid, "
		   		+ "please provide any of accepted, preparing_food, on_the_way, delivered");	
		}
		
		DeliveryStatusDto deliveryStatusDto = new DeliveryStatusDto();
		deliveryStatusDto.setOrderId(deliveryStatusModel.getOrderId());
		deliveryStatusDto.setOrderStatus(deliveryStatusModel.getOrderStatus());
		
		String updateOrderStatus = orderService.updateDeliveryStatus(deliveryStatusDto);
		
		return updateOrderStatus;
	}
	
	/**
	 * To get the order status 
     * @param orderId
	   
	 * @return String
	 *  order status with respect to orderId
	 * @throws OrderNotFoundException
	 */
	
	@RequestMapping(value = "/order/{orderId}/status", method = RequestMethod.GET)
	public String getOrderStatus(@PathVariable("orderId") long orderId) 
        throws OrderNotFoundException {
		   
	   OrderDto orderDto =  orderService.getOrderDetails(orderId);
	   
	   return orderDto.getOrderStatus();	
	}
	
	/**
	 * To get the list of active delivery persons details
     * @param no parameter 
	   
	 * @return List<DeliveryPersonDetails>
	 *  list active of delivery persons details.
	 * @throws DeliveryManRetrievalException
	 */
	
	@RequestMapping(value = "/delivery/active/details")
	public List<DeliveryPersonDetails> getActiveDeliveryPersonsDetails()
	   throws DeliveryManRetrievalException {
		
	   List<DeliveryPersonDto> activeDeliveryMans = 
	       orderService.getActiveDeliveryPersons();
	   
	   List<DeliveryPersonDetails> activeShipperDetails = 
           activeDeliveryMans.stream().map(
           shipper -> new DeliveryPersonDetails(shipper.getDeliveryPersonId(),
        		 shipper.getContact(), shipper.getPersonStatus()))
                 .collect(Collectors.toList());
	   
	   return activeShipperDetails;
	}
	
	/**
	 * To get the state of a delivery person. 
	 * If the state of delivery person is active or inactive then simply return the id and status as active.
	 * If the state of delivery person is engaged then it will return orderID, he is delivering and the time left to deliver and 
	 * the expected delivery time is already over then an user message will be displayed as "Delivery will take more than usual time" 
     * @param no parameter 
	 * @return List<DeliveryPersonDetails>
	 *  list active of delivery persons details.
	 * @throws DeliveryManRetrievalException
	 */
	@RequestMapping(value = "/delivery/{deliveryPersonId}/state", method = RequestMethod.GET)
	public DeliveryPersonStateResp getStateOfDeliveryPerson(
	    @PathVariable("deliveryPersonId") long deliveryPersonId) throws ShipperException {
		
		DeliveryPersonStateDto shipperStateDto = 
		    orderService.getDeliveryPersonState(deliveryPersonId);
		DeliveryPersonStateResp shipperStateResp = new DeliveryPersonStateResp();
		shipperStateResp.setDeliveryPersonId(shipperStateDto.getDeliveryPersonId());
		shipperStateResp.setPersonStatus(shipperStateDto.getPersonStatus());
		shipperStateResp.setTimeLeftToDeliver(shipperStateDto.getTimeLeftToDeliver());
		shipperStateResp.setOrderId(shipperStateDto.getOrderId());
		shipperStateResp.setExpectedDeliveryTime(shipperStateDto.getEpectedDeliveryTime());
		shipperStateResp.setMessage(shipperStateDto.getMessage());
		shipperStateResp.setOrderTime(shipperStateDto.getOrderTime());
		
		return shipperStateResp;
	}	

}
