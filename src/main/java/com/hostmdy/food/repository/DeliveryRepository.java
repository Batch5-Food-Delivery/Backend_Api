package com.hostmdy.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
	
	Optional<Delivery> findById(Long id);
	
	// For Driver
	List<Delivery> findByDriverAndCompletedTrueOrderByIdDesc(User driver);
	List<Delivery> findByDriverAndCompletedFalseOrderByIdDesc(User driver);
	Optional<Delivery> findByDriverAndId(User driver, Long id);
	
	// For User
	List<Delivery> findByCustomerAndCompletedTrue(User customer);
	List<Delivery> findByCustomerAndCompletedFalse(User customer);
	
	// For Restaurant
	List<Delivery> findByRestaurantAndCompletedTrue(Restaruant restaurant);
	List<Delivery> findByRestaurantAndCompletedFalse(Restaruant restaurant);
}
