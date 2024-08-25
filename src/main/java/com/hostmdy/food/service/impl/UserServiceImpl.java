package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.hostmdy.food.domain.Cart;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.domain.security.Role;
import com.hostmdy.food.domain.security.UserRoles;
import com.hostmdy.food.exception.UserAlreadyExistsException;
import com.hostmdy.food.repository.RoleRepository;
import com.hostmdy.food.repository.UserRepository;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public Optional<User> getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId);
	}
	
	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return user.get();
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
		
		// Check existing users
		if (isEmailExists(user.getEmail())) {
			throw new UserAlreadyExistsException("Email already exists");
		}
		if (isUsernameExists(user.getUsername())) {
			throw new UserAlreadyExistsException("Username already exists");
		}
		
		// Password Encoding
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		// Assigning Role
		Optional<Role> roleOptional = roleRepository.findByName("USER");
		if(roleOptional.isEmpty()) {
			throw new NullPointerException("ROLE_USER is not found");
		}
		
		Role role = roleOptional.get();
		UserRoles userRoles = new UserRoles(user, role);
		user.getUserRoles().add(userRoles);
		role.getUserRoles().add(userRoles);
		
		// Configuring Cart
		Cart cart = new Cart();
		cart.setUser(user);
		user.setCart(cart);
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		
		return saveUser(user);
	}
}
