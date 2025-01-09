package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}