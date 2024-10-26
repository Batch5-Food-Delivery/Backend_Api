package com.hostmdy.food.service.impl;

import java.lang.StackWalker.Option;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.repository.DeliveryRepository;
import com.hostmdy.food.repository.OrderRepository;
import com.hostmdy.food.repository.UserRepository;
import com.hostmdy.food.service.DeliveryService;
import com.hostmdy.food.service.UserService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
	
	private final DeliveryRepository deliveryRepository;
	private final UserRepository userRepository;
	
	// For Driver
	@Override
	public Optional<Delivery> getDeliveryById(Long id) {
		// TODO Auto-generated method stub
		return deliveryRepository.findById(id);
	}

	@Override
	public List<Delivery> getCompletedDeliveriesByDriver(User driver) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByDriverAndCompletedTrue(driver);
	}

	@Override
	public List<Delivery> getCurrentDeliveriesByDriver(User driver) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByDriverAndCompletedFalse(driver);
	}

	@Override
	public Delivery completeDelivery(Long deliveryId, Principal principal) {
		// TODO Auto-generated method stub 
		User user = userRepository.findByUsername(principal.getName()).get();
		Optional<Delivery> deliveryOptional;
		if (user.isAdmin()) {
			deliveryOptional = deliveryRepository.findById(deliveryId);
		} else {
			deliveryOptional = deliveryRepository.findByDriverAndId(user, deliveryId);
		}
		
		if (deliveryOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("The delivery is not found");
		}
		
		Delivery delivery = deliveryOptional.get();
		delivery.setCompleted(true);
		return deliveryRepository.save(delivery);
	}
	
	
	// For Customer
	@Override
	public List<Delivery> getCurrentDeliveriesByCustomer(User customer) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByCustomerAndCompletedFalse(customer);
	}

	@Override
	public List<Delivery> getCompletedDeliveriesByCustomer(User customer) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByCustomerAndCompletedTrue(customer);
	}

	
	// For Restaurant
	@Override
	public List<Delivery> getCurrentDeliveriesByRestaurant(Restaruant restaurant) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByRestaurantAndCompletedFalse(restaurant);
	}

	@Override
	public List<Delivery> getCompletedDeliveriesByRestaurant(Restaruant restaurant) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByRestaurantAndCompletedTrue(restaurant);
	}
	
	@Override
	public Delivery createDelivery(Order order, Long driverId) {
		
		Optional<User> driver = userRepository.findById(driverId);
		if (driver.isEmpty() || !driver.get().isDriver()) {
			throw new DatabaseRecordNotFoundException("Driver not found");
		}
		
		Delivery delivery = new Delivery();
		delivery.setRestaurant(order.getRestaurant());
		delivery.setRestaurantAddress(order.getRestaurant().getAddress());
		delivery.setOrder(order);
		delivery.setCustomer(order.getCustomer());
		delivery.setDestination(order.getDestination());
		delivery.setDriver(driver.get());
		
		return deliveryRepository.save(delivery);
	}

}
