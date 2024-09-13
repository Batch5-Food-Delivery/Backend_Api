package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.repository.RestaruantRepository;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RestaruantServiceImpl implements RestaruantService  {
	
	private final RestaruantRepository resRepo;
	private final UserService userService;
	
	private User getUser(String userName) {
		User user = userService.getUserByUsername(userName);
		return user;
	}
	
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
	public Restaruant saveRestaruant(Restaruant restaruant,String userName) {
		// TODO Auto-generated method stub
		User user = getUser(userName);
		restaruant.setOwner(user);
		return resRepo.save(restaruant);
		
	}

	@Override
	public void deleteRestaurnt(Long id) {
		// TODO Auto-generated method stub
		resRepo.deleteById(id);
	}

	@Override
	public Restaruant getRestaurantByIdAndOwnername(Long id, String ownername) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateRestaurantOwner(Long restaurantId, String ownername) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Restaruant getRestaurantByIdAndOwnername(Long id, String ownername) {
//		// TODO Auto-generated method stub
//		User owner = userService.getUserByUsername(ownername);
//		Optional<Restaruant> restaurant = resRepo.findByIdAndOwner(id, owner);
//		if (restaurant.isEmpty()) {
//			throw new DatabaseRecordNotFoundException("Restaurant you're trying to access"
//					+ "is not available");
//		}
//		
//		return restaurant.get();
//	}
	
//	@Override
//	public void validateRestaurantOwner(Long restaurantId, String ownername) {
//		
//		User owner = userService.getUserByUsername(ownername);
//		Optional<Restaruant> restaurant = resRepo.findByIdAndOwner(restaurantId, owner);
//		if (restaurant.isEmpty()) {
//			throw new DatabaseRecordNotFoundException("Restaurant you're trying to access"
//					+ "is not available");
//		}
//	}

	
}
