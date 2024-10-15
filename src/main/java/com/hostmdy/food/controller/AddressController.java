package com.hostmdy.food.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Address;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.repository.AddressRepository;
import com.hostmdy.food.repository.UserRepository;
import com.hostmdy.food.service.AddressService;
import com.hostmdy.food.service.MenuService;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class AddressController {

	private final UserService userService;
	private final AddressService addressService;
	
	@GetMapping("/myAddresses")
	public ResponseEntity<List<Address>> getUserAddresses(Principal principal) { 
		
		User user = userService.getUserByUsername(principal.getName());
		return ResponseEntity.ok(addressService.myAddresses(user));
		
	}
	
	@PostMapping("/newAddress")
	public ResponseEntity<Address> createNewAddress(@RequestBody Address address, Principal principal) {
		User user = userService.getUserByUsername(principal.getName());
		return ResponseEntity.ok(addressService.createAddress(address, user));
	}
}
