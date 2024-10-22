package com.hostmdy.food.service;

import java.util.List;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface OrderService {

	Order getOrderByRestaurantAndId(Restaruant restaurant, Long OrderId);
	List<Order> getCurrentOrdersByRestaurant(Restaruant restaurant);
	List<Order> getCompletedOrdersByRestaurant(Restaruant restaurant);
	Order saveOrder(Order order);
}
