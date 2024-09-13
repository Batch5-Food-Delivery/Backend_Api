package com.hostmdy.food.service;

import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


import com.hostmdy.food.domain.Food;

public interface FoodService {
	
	Optional<Food> getFoodById(Long id);
	
	List<Food> getAllFood();
	
	Food saveFood(Food food);
	
	void deleteFood(Long id);
}
