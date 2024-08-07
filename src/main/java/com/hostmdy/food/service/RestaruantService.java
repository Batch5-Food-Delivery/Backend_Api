package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Restaruant;


public interface RestaruantService {
	
    Optional<Restaruant> getRestaruantById(Long id);
	
	List<Restaruant> getAllRestaruant();
	
	Restaruant saveRestaruant(Restaruant restaruant);
	
	void deleteRestaurnt(Long id);


}
