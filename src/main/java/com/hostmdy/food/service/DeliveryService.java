package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.User;

public interface DeliveryService {
	Optional<Delivery> getDeliveryById(Long id);
	
	
	List<Delivery> getCurrentDeliveriesByDriver(User driver); 
	List<Delivery> getCompletedDeliveriesByDriver(User driver); 
}
