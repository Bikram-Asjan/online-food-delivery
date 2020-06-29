package com.bikram.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bikram.io.entity.OrderDeliveryEntity;

public interface OrderDeliveryRepository extends 
    CrudRepository<OrderDeliveryEntity, Long> {
	
	@Query("select od from OrderDeliveryEntity od where od.orderId = :orderId")
	public OrderDeliveryEntity findByOrderId(@Param("orderId") long orderId);
	
	/**
	 * @Query("select order_time, expected_delivery_time from order_delivery where 
	 * delivery_person_id=4 AND NOT order_status='DELIVERED'",
	 * nativeQuery = true)
	 * @param orderId
	 * @param orderStatus
	 * @return
	 */
	@Query("select od from OrderDeliveryEntity od where od.deliveryPersonId = :deliveryPersonId AND NOT orderStatus='delivered'")
	public OrderDeliveryEntity findByShipperIdAndStatusNotDelivered(
        @Param("deliveryPersonId")long deliveryPersonId);
    
}
