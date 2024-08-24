package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.User;

public interface UserService {

	Optional<User> getUserById(Long id);
	List<User> getAllUsers();
	User createUser(User user);
	Boolean isUsernameExists(String username);
	Boolean isEmailExists(String email);
	
}
