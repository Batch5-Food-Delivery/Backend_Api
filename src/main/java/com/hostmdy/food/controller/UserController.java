package com.hostmdy.food.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.config.JwtTokenProvider;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.exception.UserAlreadyExistsException;
import com.hostmdy.food.payload.LoginRequest;
import com.hostmdy.food.payload.LoginResponse;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
	
private final static String TOKEN_PREFIX = "Bearer ";
	
private final UserService userService;
private final AuthenticationManager authManager;
private final JwtTokenProvider tokenProvider;	
	
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

		System.out.println("Im in the controller");
	     User newUser = userService.createUser(user);
	     return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		
		// Check if the user entered email instead of username
		Optional<User> userOptional = userService.getUserByEmail(request.getUsername());
		if (userOptional.isPresent()) {
			request.setUsername(userOptional.get().getUsername());
		}
		
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())	
					);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		User user = userService.getUserByUsername(request.getUsername());
		
		String token = TOKEN_PREFIX+tokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new LoginResponse(token,user,user.getRoles(),true));		
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
	
	@GetMapping("/availableDrivers")
	public ResponseEntity<List<User>> findAllAvailableDrivers() {
		return ResponseEntity.ok(userService.getAllAvailableDrivers());
	}
	
	@PutMapping("/applyDriver")
	public ResponseEntity<User> applyForDriver(Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		return ResponseEntity.ok(userService.applyDriver(user));
	}
	
	@PutMapping("/availableStatus")
	public ResponseEntity<Boolean> switchAvailableStatus(Principal principal, @RequestParam Boolean available) {
		User user = userService.getUserByUsername(principal.getName());
		return ResponseEntity.ok(userService.availableSwitch(user, available).getAvailable());
	}
}
