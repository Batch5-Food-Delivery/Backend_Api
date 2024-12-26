package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;


public interface RestaruantService {
	
    Optional<Restaruant> getRestaruantById(Long id);
    
    Restaruant getRestaurantByIdAndOwnername(Long id, String ownername);
	
	List<Restaruant> getAllRestaruant();
	
	Restaruant saveRestaruant(Restaruant restaruant);
	
	Restaruant updateRestaurant(Restaruant restaurant);
	
	void deleteRestaurnt(Long id);
	
	boolean isRestaurantOwner(Long restaurantId, String ownerName);

	void validateRestaurantOwner(Long restaurantId, String ownername);
	
	List<Restaruant> getPendingRestaurants();
	
	Restaruant acceptRestaurant(Long restaurantId);
}
