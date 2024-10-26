package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface OrderService {

	Optional<Order> getOrderById(Long orderId);
	Order getOrderByRestaurantAndId(Restaruant restaurant, Long OrderId);
	List<Order> getCurrentOrdersByRestaurant(Restaruant restaurant);
	List<Order> getCompletedOrdersByRestaurant(Restaruant restaurant);
	Order saveOrder(Order order);
	Order completeOrder(Long orderId, Long driverId);
}
