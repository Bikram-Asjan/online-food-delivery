package com.bikram.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.bikram.io.entity.OrderEntity;


public interface OrderRepository extends JpaRepository<OrderEntity, Long>{

}
