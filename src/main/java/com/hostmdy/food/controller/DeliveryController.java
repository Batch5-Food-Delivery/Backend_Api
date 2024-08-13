package com.hostmdy.food.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/delivery")
public class DeliveryController {
	
	private final DeliveryService deliveryService;
	
	@GetMapping("/byDriver/{driver_id}")
	public ResponseEntity<List<Delivery>> getDeliveriesByDriver(@PathVariable Long driver_id){
		
		User user = new User();
		user.setId(driver_id);
		
		List<Delivery> deliveryList = deliveryService.getCurrentDeliveriesByDriver(user);
		return ResponseEntity.ok(deliveryList);
 	}
}
