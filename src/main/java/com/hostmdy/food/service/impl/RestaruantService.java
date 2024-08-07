package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.repository.RestaruantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaruantService implements com.hostmdy.food.service.RestaruantService  {
	
	private final RestaruantRepository resRepo;

	@Override
	public Optional<Restaruant> getRestaruantById(Long id) {
		// TODO Auto-generated method stub
		return resRepo.findById(id);
	}

	@Override
	public List<Restaruant> getAllRestaruant() {
		// TODO Auto-generated method stub
		return (List<Restaruant>) resRepo.findAll();
	}

	@Override
	public Restaruant saveRestaruant(Restaruant restaruant) {
		// TODO Auto-generated method stub
		return resRepo.save(restaruant);
	}

	@Override
	public void deleteRestaurnt(Long id) {
		// TODO Auto-generated method stub
		resRepo.deleteById(id);
	}

	
}
