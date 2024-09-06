package com.hostmdy.food.service.impl;



import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.repository.FoodRepository;
import com.hostmdy.food.repository.OrderRepository;
import com.hostmdy.food.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	 
	    private OrderRepository orderRepository;

	@Override
	public Order saveOrder(Order order) {
		order.getItems().forEach(item -> item.setOrder(order));
        return orderRepository.save(order);
		
	}

}
