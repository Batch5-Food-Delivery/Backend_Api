package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Region;

public interface RegionService {
	
Optional<Region> getRegionById(Long id);
	
	List<Region> getAllRegion();
	
	Region saveRegion(Region food);
	
	void deleteRegion(Long id);

}
