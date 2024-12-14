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
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
@CrossOrigin("http://localhost:3000")
public class RestaruantController {
	
	private final Environment env;
   private final RestaruantService resService;
   private final UserService userService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Restaruant>> getAllRes(){
		List<Restaruant> resList = resService.getAllRestaruant();
		return ResponseEntity.ok(resList);
 	}
	
	@GetMapping("/{resId}")
	public ResponseEntity<Restaruant> getRestaurantById(@PathVariable Long resId){
		Optional<Restaruant> res= resService.getRestaruantById(resId);
		if(res.isEmpty()) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(res.get());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Restaruant> createFood(@RequestBody Restaruant res, Principal principal) {
		res.setOwner(userService.getUserByUsername(principal.getName()));
		Restaruant createdRes = resService.saveRestaruant(res);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRes);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Restaruant> updateFood(@RequestBody Restaruant res){
		
		if(res.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(resService.saveRestaruant(res));
	}
	
	@DeleteMapping("/{resId}/delete")
	public ResponseEntity<Long> deleteFood(@PathVariable Long resId){
		
		
		Optional<Restaruant> res = resService.getRestaruantById(resId);
		
		if(res.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		resService.deleteRestaurnt(resId);
		
		return ResponseEntity.ok(resId);
	
	}

	@GetMapping("/{resId}/isOwner")
	public ResponseEntity<Boolean> isOwner(@PathVariable Long resId, Principal principal) {
		return ResponseEntity.ok(resService.isRestaurantOwner(resId, principal.getName()));
	}
	
	@PostMapping("uploadImage/{restaurantId}")
	public ResponseEntity<String> uploadRestaurantImage(@RequestParam("file") MultipartFile file,@PathVariable Long restaurantId, Principal principal) 
			throws IOException{
		
		Restaruant restaurant = resService.getRestaurantByIdAndOwnername(restaurantId, principal.getName());
        
		String uploadPath = env.getProperty("restaurant_image_upload_path");

        String fileName = restaurantId+".jpg";
        
        Path filePath = Path.of(uploadPath+fileName);
        
        saveImage(file, filePath);

        
        restaurant.setProfile(fileName);
        resService.saveRestaruant(restaurant);
        
        return ResponseEntity.ok("Success");
        

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
        ClassPathResource resource = new ClassPathResource("static/images/restaurant/" + imageName);
        byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
    
    @GetMapping("/pendingRestaurants")
    public ResponseEntity<List<Restaruant>> pendingRestaurants() {
    	return ResponseEntity.ok(resService.getPendingRestaurants());
    }
    
    @PutMapping("/acceptRestaurant")
    public ResponseEntity<Restaruant> acceptRestaurant(@RequestParam Long id) {
    	return ResponseEntity.ok(resService.acceptRestaurant(id));
    }
	
}
