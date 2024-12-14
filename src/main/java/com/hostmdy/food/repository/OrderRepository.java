package com.hostmdy.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface OrderRepository extends CrudRepository<Order, Long> {

	Optional<Order> findByRestaurantAndId(Restaruant restaurant, Long id);
	List<Order> findByRestaurantAndCompletedTrue(Restaruant restaurant);
	List<Order> findByRestaurantAndCompletedFalse(Restaruant restaurant);
	List<Order> findByCustomer(User customer);
}
