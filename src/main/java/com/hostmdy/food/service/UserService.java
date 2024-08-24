package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.User;

public interface UserService {
	
	
    Optional<User> getUserById(Long userId);
	
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User createUser(User user);
	
	void deleteUserById(Long userId);

}
