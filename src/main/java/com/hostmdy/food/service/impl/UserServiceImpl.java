package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.hostmdy.food.domain.Cart;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.repository.UserRepository;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository userRepository;

	@Override
	public Optional<User> getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return (List<User>) userRepository.findAll();
	}

	@Override
	public Boolean isUsernameExists(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username).isPresent();
	}

	@Override
	public Boolean isEmailExists(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email).isPresent();
	}
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(Long userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		
		Cart cart = new Cart();
		
		cart.setUser(user);
		user.setCart(cart);
		return saveUser(user);
	}


}
