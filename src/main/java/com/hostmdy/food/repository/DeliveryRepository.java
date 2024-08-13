package com.hostmdy.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.User;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
	
	Optional<Delivery> findById(Long id);
	
	// For Driver
	List<Delivery> findByDriverAndCompletedTrue(User driver);
	List<Delivery> findByDriverAndCompletedFalse(User driver);
	
	// For User
	List<Delivery> findByCustomerAndCompletedTrue(User driver);
	List<Delivery> findByCustomerAndCompletedFalse(User driver);
}
