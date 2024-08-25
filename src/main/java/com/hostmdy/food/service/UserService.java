package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.User;

public interface UserService {

	List<User> getAllUsers();
	Boolean isUsernameExists(String username);
	Boolean isEmailExists(String email);
	Optional<User> getUserByEmail(String email);
	User getUserByUsername(String username);
	
	
	
    Optional<User> getUserById(Long userId);
		
	User saveUser(User user);
	
	User createUser(User user);
	
	void deleteUserById(Long userId);

}
