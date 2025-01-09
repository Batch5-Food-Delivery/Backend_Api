package com.hostmdy.food.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.domain.Restaruant;

public interface FoodRepository extends CrudRepository<Food, Long> {
	
	Optional<Food> findByIdAndRestaurant(Long id, Restaruant restaurant);
}
