package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.OrderItem;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface OrderService {

	Order getOrderByRestaurantAndId(Restaruant restaurant, Long OrderId);
	Order saveOrder(Order order);
	Optional<Order> getOrderById(Long orderId);
	void deleteOrderById(Long orderId);
	 public List<OrderItem> getCartItemsByOrderId(Long orderId) ;
}

