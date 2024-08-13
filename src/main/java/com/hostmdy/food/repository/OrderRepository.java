package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
