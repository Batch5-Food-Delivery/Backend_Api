package com.hostmdy.food.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.service.RestaruantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
@CrossOrigin("http://localhost3000")
public class RestaruantController {
	
   private final RestaruantService resService;
	
	
	@GetMapping("/all")
	public List<Restaruant> getAllRes(){
		List<Restaruant> resList = resService.getAllRestaruant();
		return resList;
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
	public ResponseEntity<Restaruant> createFood(@RequestBody Restaruant res) {
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
	
}
