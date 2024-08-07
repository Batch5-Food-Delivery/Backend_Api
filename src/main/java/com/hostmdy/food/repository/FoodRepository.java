package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {
	
	
}
