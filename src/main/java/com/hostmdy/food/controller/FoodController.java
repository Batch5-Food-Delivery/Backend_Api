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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.service.FoodService;
import com.hostmdy.food.service.RestaruantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
@CrossOrigin("http://localhost:3000")
public class FoodController {
	
	private final Environment env;
	private final FoodService foodService;
	private final RestaruantService restaurantService;

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
	public ResponseEntity<Food> createFood(@RequestBody Food food, Principal principal) {
		
		//restaurantService.validateRestaurantOwner(food.getRestaurant().getId(), principal.getName());
		
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
	
	@PostMapping("uploadImage/{foodId}")
	public ResponseEntity<String> uploadFoodImage(@RequestParam("file") MultipartFile file,@PathVariable Long foodId) 
			throws IOException{
		
		
		
		String uploadPath = env.getProperty("food_image_upload_path");

        String fileName = foodId+".jpg";
        
        Path filePath = Path.of(uploadPath+fileName);
        
        saveImage(file, filePath);
        
        Optional<Food> foodOptional = foodService.getFoodById(foodId);
        
        if(foodOptional.isEmpty()) {
        	throw new DatabaseRecordNotFoundException(fileName);
        }
        
        Food food = foodOptional.get();
        food.setPicture(fileName);
        foodService.saveFood(food);
        
		return null;
        

	}
	
	private void saveImage(MultipartFile file, Path filePath) {
		 try {
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
