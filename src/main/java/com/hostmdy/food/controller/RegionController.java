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
import com.hostmdy.food.domain.Region;
import com.hostmdy.food.service.RegionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {
	
	private final RegionService regionService;
	
	
	@GetMapping("/all")
	public List<Region> getAllRegion(){
		List<Region> regionList = regionService.getAllRegion();
		return regionList;
 	}
	
	@GetMapping("/{regionId}")
	public ResponseEntity<Region> getFoodById(@PathVariable Long regionId){
		Optional<Region> region= regionService.getRegionById(regionId);
		if(region.isEmpty()) {
			return ResponseEntity.status(404).build();
		}
		return ResponseEntity.ok(region.get());
	}
	
	@PostMapping("/create")
	public ResponseEntity<Region> createFood(@RequestBody Region region) {
		Region createdRegion = regionService.saveRegion(region);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRegion);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Region> updateFood(@RequestBody Region region){
		
		if(region.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(regionService.saveRegion(region));
	}
	
	@DeleteMapping("/{regionId}/delete")
	public ResponseEntity<Long> deleteFood(@PathVariable Long regionId){
		
		
		Optional<Region> region = regionService.getRegionById(regionId);
		
		if(region.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		regionService.deleteRegion(regionId);
		
		return ResponseEntity.ok(regionId);
	
	}
	
	
	
}
