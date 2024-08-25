package com.hostmdy.food.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.service.DeliveryService;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myRestaurant")
public class RestaurantDeliveryController {

	private final DeliveryService deliveryService;
	private final RestaruantService restaurantService;
	
	@GetMapping("{restaurantId}/deliveries")
	public ResponseEntity<List<Delivery>> getDeliveriesByRestaurant(Principal principal, @PathVariable Long restaurantId){
		
		Restaruant restaurant = restaurantService.getRestaurantByIdAndOwnername(restaurantId, principal.getName());
		
		List<Delivery> deliveryList = deliveryService.getCurrentDeliveriesByRestaurant(restaurant);
		return ResponseEntity.ok(deliveryList);
 	}
	
	@GetMapping("{restaurantId}/deliveries/history")
	public ResponseEntity<List<Delivery>> getCompletedDeliveriesByCustomer(Principal principal, @PathVariable Long restaurantId) {
		
		Restaruant restaurant = restaurantService.getRestaurantByIdAndOwnername(restaurantId, principal.getName());
		
		List<Delivery> deliveryList = deliveryService.getCompletedDeliveriesByRestaurant(restaurant);
		return ResponseEntity.ok(deliveryList);
		
	}
}
