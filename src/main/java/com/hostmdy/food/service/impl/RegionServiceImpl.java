package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.hostmdy.food.domain.Region;
import com.hostmdy.food.repository.RegionRepository;
import com.hostmdy.food.service.RegionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {
	
	private final RegionRepository regionRepository;

	@Override
	public Optional<Region> getRegionById(Long id) {
		// TODO Auto-generated method stub
		return regionRepository.findById(id);
	}

	@Override
	public List<Region> getAllRegion() {
		// TODO Auto-generated method stub
		return (List<Region>) regionRepository.findAll();
	}

	@Override
	public Region saveRegion(Region food) {
		// TODO Auto-generated method stub
		return regionRepository.save(food);
	}

	@Override
	public void deleteRegion(Long id) {
		// TODO Auto-generated method stub
		regionRepository.deleteById(id);
	}
	

}
