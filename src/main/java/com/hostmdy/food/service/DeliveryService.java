package com.hostmdy.food.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface DeliveryService {
	Optional<Delivery> getDeliveryById(Long id);
	
	
	List<Delivery> getCurrentDeliveriesByDriver(User driver); 
	List<Delivery> getCompletedDeliveriesByDriver(User driver); 
	Delivery completeDelivery(Long deliveryId, Principal principal);
	
	List<Delivery> getCurrentDeliveriesByCustomer(User customer);
	List<Delivery> getCompletedDeliveriesByCustomer(User customer);
	
	List<Delivery> getCurrentDeliveriesByRestaurant(Restaruant restaurant);
	List<Delivery> getCompletedDeliveriesByRestaurant(Restaruant restaurant);
	Delivery createDelivery(Order order, Long driverId);
}
