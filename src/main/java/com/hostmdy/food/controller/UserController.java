package com.hostmdy.food.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.food.domain.User;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
private final UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> usersList = userService.getAllUsers();
		if(usersList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usersList);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId){
		Optional<User> userOptional = userService.getUserById(userId);
		if(userOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userOptional.get());
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody User user){
		return ResponseEntity.status(201).body(userService.createUser(user));
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		if(user.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(201).body(userService.saveUser(user));
	}
	
	@DeleteMapping("/{userId}/delete")
	public ResponseEntity<Long> deleteUser(@PathVariable Long userId){
		Optional<User> userOptional = userService.getUserById(userId);
		if(userOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUserById(userId);
		return ResponseEntity.ok(userId);
	}

}
