package com.hostmdy.food.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.service.FoodService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageController {
	
	private final Environment env;
	private final FoodService foodService;
	
	@PostMapping("upload/food/{food_id}")
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
	
	@GetMapping("/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        ClassPathResource resource = new ClassPathResource("static/images/product/" + imageName);
        
        try {
        	 byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

             return ResponseEntity.ok()
                     .contentType(MediaType.IMAGE_JPEG)
                     .body(imageBytes);
        } catch (IOException e) {
			// TODO: handle exception
        	throw new DatabaseRecordNotFoundException("Can't find the image");
		}
       
    }
	
	
	
	
}
