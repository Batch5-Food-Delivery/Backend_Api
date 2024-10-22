package com.hostmdy.food.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.service.OrderService;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	 @Autowired
	 private final OrderService orderService;
	 private final UserService userService;
	 private final RestaruantService restaurantService;
	 

	 	@PostMapping("/create")
	    public ResponseEntity<Order> createOrder(@RequestBody Order order, Principal principal) {
	       order.setCustomer(userService.getUserByUsername(principal.getName()));
	       return ResponseEntity.ok(orderService.saveOrder(order));
	    }
	 	
	 	@GetMapping("restaurant/{restaurantId}/current") 
	 	public ResponseEntity<List<Order>> getCurrentOrdersByRestaurant(@PathVariable Long restaurantId, Principal principal) {
	 		Restaruant res = restaurantService.getRestaurantByIdAndOwnername(restaurantId, principal.getName());
	 		return ResponseEntity.ok(orderService.getCurrentOrdersByRestaurant(res));
	 	}
	 	
	 	@GetMapping("restaurant/{restaurantId}/history") 
	 	public ResponseEntity<List<Order>> getCompletedOrdersByRestaurant(@PathVariable Long restaurantId, Principal principal) {
	 		Restaruant res = restaurantService.getRestaurantByIdAndOwnername(restaurantId, principal.getName());
	 		return ResponseEntity.ok(orderService.getCompletedOrdersByRestaurant(res));
	 	}
}
