package com.hostmdy.food.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hostmdy.food.domain.Food;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.service.FoodService;
import com.hostmdy.food.service.ImageService;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
@CrossOrigin("http://localhost:3000")
public class FoodController {
	
	private final Environment env;
	private final FoodService foodService;
	private final RestaruantService restaurantService;
	private final ImageService imageService;
	private final UserService userService;

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
	
	@PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Food> createFood(@RequestPart("food") String foodJson, @RequestPart("image") Optional<MultipartFile> image,
			Principal principal) throws IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
	    Food food = objectMapper.readValue(foodJson, Food.class);
		
		restaurantService.validateRestaurantOwner(food.getRestaurant().getId(), principal.getName());
		
		Food createdFood = foodService.saveFood(food);
		
		if(!image.isEmpty()) {
			uploadFoodImage(image.get(), createdFood);
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdFood);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Food> updateFood(@RequestPart("food") String foodJson, @RequestPart("image") Optional<MultipartFile> image,
			Principal principal) throws IOException{
		
		ObjectMapper objectMapper = new ObjectMapper();
	    Food food = objectMapper.readValue(foodJson, Food.class);
	    
	    if(food.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		
	    if (!userService.getUserByUsername(principal.getName()).isAdmin()) {
			restaurantService.validateRestaurantOwner(food.getRestaurant().getId(), principal.getName());

	    }
		
		Food updatedFood = foodService.updateFood(food);
		
		if(!image.isEmpty()) {
			uploadFoodImage(image.get(), updatedFood);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(updatedFood);
	}
	
	@DeleteMapping("/{foodId}/delete")
	public ResponseEntity<Long> deleteFood(@PathVariable Long foodId, Principal principal){
		
		Optional<Food> food = foodService.getFoodById(foodId);
		
		if(food.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		if (!userService.getUserByUsername(principal.getName()).isAdmin()) {
			restaurantService.validateRestaurantOwner(food.get().getRestaurant().getId(), principal.getName());
		}
		
		foodService.deleteFood(foodId);
		
		return ResponseEntity.ok(foodId);
	
	}
	
	
	public void uploadFoodImage(MultipartFile image, Food food) {
		
		String uploadPath = env.getProperty("food_image_upload_path");
		
		String imgName = imageService.saveImage(image, food.getId(), uploadPath);
        
        food.setPicture(imgName);
        foodService.saveFood(food);        
	}
	
	@GetMapping("image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/food/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
	
}
