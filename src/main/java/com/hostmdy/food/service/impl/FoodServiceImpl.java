package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.repository.FoodRepository;
import com.hostmdy.food.service.FoodService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
	
	private final FoodRepository foodRepository;
	
	@Override
	public Optional<Food> getFoodById(Long id) {
		// TODO Auto-generated method stub
		return foodRepository.findById(id);
		
	}
	
	@Override
	public Optional<Food> getFoodByIdAndRestaurant(Long id, Restaruant restaurant) {
		return foodRepository.findByIdAndRestaurant(id, restaurant);
	}

	@Override
	public List<Food> getAllFood() {
		// TODO Auto-generated method stub
		return (List<Food>) foodRepository.findAll();
	}

	@Override
	public Food saveFood(Food food) {
		// TODO Auto-generated method stub
		return foodRepository.save(food);
	}

	@Override
	@Transactional
	public void deleteFood(Long id) {
		// TODO Auto-generated method stub
		 foodRepository.deleteById(id);
	}

	@Override
	public Food updateFood(Food food) {
		Optional<Food> oldFoodOptional = getFoodById(food.getId());
		if (oldFoodOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Food not found");
		}
		
		Food oldFood = oldFoodOptional.get();
		food.setRestaurant(oldFood.getRestaurant());
		food.setMenu(oldFood.getMenu());
		food.setPicture(oldFood.getPicture());
		return saveFood(food);
	}

}
