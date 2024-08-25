package com.hostmdy.food.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.service.DeliveryService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class CustomerDeliveryController {
	
	private final DeliveryService deliveryService;
	private final UserService userService;
	
	@GetMapping("/deliveries")
	public ResponseEntity<List<Delivery>> getDeliveriesByCustomer(Principal principal){
		
		User customer = userService.getUserByUsername(principal.getName());
		
		List<Delivery> deliveryList = deliveryService.getCurrentDeliveriesByCustomer(customer);
		return ResponseEntity.ok(deliveryList);
 	}
	
	@GetMapping("/deliveries/history")
	public ResponseEntity<List<Delivery>> getCompletedDeliveriesByCustomer(Principal principal) {
		
		User customer = userService.getUserByUsername(principal.getName());
		List<Delivery> deliveList = deliveryService.getCompletedDeliveriesByCustomer(customer);
		return ResponseEntity.ok(deliveList);
		
	}
}
