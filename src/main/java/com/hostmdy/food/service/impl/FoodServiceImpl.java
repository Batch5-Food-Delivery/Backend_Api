package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Food;
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
	public void deleteFood(Long id) {
		// TODO Auto-generated method stub
		 foodRepository.deleteById(id);
	}

}
