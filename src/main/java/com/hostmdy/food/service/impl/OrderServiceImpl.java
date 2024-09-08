package com.hostmdy.food.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.repository.DeliveryRepository;
import com.hostmdy.food.repository.OrderRepository;
import com.hostmdy.food.service.OrderService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;

	@Override
	public Order getOrderByRestaurantAndId(Restaruant restaurant, Long OrderId) {
		
		Optional<Order> order = orderRepository.findByRestaurantAndId(restaurant, OrderId);
		if (order.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Your restaurant doesn't have this order");
		}
		
		return order.get();
	}
	
	@Override
	public Order saveOrder(Order order) {
		order.getItems().forEach(item -> item.setOrder(order));
        return orderRepository.save(order);
		
	}
}
