package com.hostmdy.food.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.service.DeliveryService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
@CrossOrigin("http://localhost:3000")
public class DriverDeliveryController {
	
	private final DeliveryService deliveryService;
	private final UserService userService;
	
	@GetMapping("/myDeliveries")
	public ResponseEntity<List<Delivery>> getDeliveriesByDriver(Principal principal){
		
		User driver = userService.getUserByUsername(principal.getName());
		
		List<Delivery> deliveryList = deliveryService.getCurrentDeliveriesByDriver(driver);
		return ResponseEntity.ok(deliveryList);
 	}
	
	@GetMapping("/myDeliveries/history")
	public ResponseEntity<List<Delivery>> getCompletedDeliveriesByDriver(Principal principal) {
		
		User driver = userService.getUserByUsername(principal.getName());
		List<Delivery> deliveList = deliveryService.getCompletedDeliveriesByDriver(driver);
		return ResponseEntity.ok(deliveList);
		
	}
	
	@PatchMapping("/deliveries/complete/{deliveryId}")
	public ResponseEntity<Delivery> completeDelivery(@PathVariable Long deliveryId, Principal principal) {
		
		return ResponseEntity.ok(deliveryService.completeDelivery(deliveryId, principal));
	}
}
