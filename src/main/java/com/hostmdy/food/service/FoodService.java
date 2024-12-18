package com.hostmdy.food.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.domain.Restaruant;

public interface FoodService {
	
	Optional<Food> getFoodById(Long id);
	
	Optional<Food> getFoodByIdAndRestaurant(Long id, Restaruant restaurant);
	
	List<Food> getAllFood();
	
	Food updateFood(Food food);
	
	Food saveFood(Food food);
	
	void deleteFood(Long id);
}
