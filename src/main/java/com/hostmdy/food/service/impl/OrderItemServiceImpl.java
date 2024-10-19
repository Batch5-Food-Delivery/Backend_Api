package com.hostmdy.food.service.impl;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.OrderItem;
import com.hostmdy.food.repository.OrderItemRepository;
import com.hostmdy.food.service.OrderItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
	
	private final OrderItemRepository orderItemRepository;

	@Override
	public OrderItem saveOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return orderItemRepository.save(orderItem);
	}

}