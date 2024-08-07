package com.hostmdy.food.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.service.FoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class FoodController {
	
	private final FoodService foodService;
	
	@GetMapping("/all")
	public List<Food> getAllFoods(){
		List<Food> foodList = foodService.getAllFood();
		return foodList;
 	}
	
	@GetMapping("/{foodId}")
	public ResponseEntity<Food> getFoodById(@PathVariable Long foodId){
		Optional<Food> food= foodService.getFoodById(foodId);
		if(food.isEmpty()) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(food.get());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Food> createFood(@RequestBody Food food) {
		Food createdFood = foodService.saveFood(food);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Food> updateFood(@RequestBody Food food){
		
		if(food.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(foodService.saveFood(food));
	}
	
	@DeleteMapping("/{foodId}/delete")
	public ResponseEntity<Long> deleteFood(@PathVariable Long foodId){
		
		
		Optional<Food> food = foodService.getFoodById(foodId);
		
		if(food.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		foodService.deleteFood(foodId);
		
		return ResponseEntity.ok(foodId);
	
	}
	
	
	
	
	
}
