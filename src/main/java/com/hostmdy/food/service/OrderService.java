package com.hostmdy.food.service;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface OrderService {

	Order getOrderByRestaurantAndId(Restaruant restaurant, Long OrderId);
	Order saveOrder(Order order);
}
