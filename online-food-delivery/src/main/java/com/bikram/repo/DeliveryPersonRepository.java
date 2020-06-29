package com.bikram.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bikram.io.entity.DeliveryPersonEntity;

public interface DeliveryPersonRepository extends CrudRepository<DeliveryPersonEntity, Long> {
   
	@Query("select d from DeliveryPersonEntity d where d.personStatus='active'")
	//@Query(value = "select * from delivery_person where person_status = 'active'", nativeQuery = true )
	public List<DeliveryPersonEntity> findAllActiveDeliveryPersons();
	
}
