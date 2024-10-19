package com.hostmdy.food.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.service.OrderService;
import com.hostmdy.food.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
	
	 @Autowired
	 private final OrderService orderService;
	 private final UserService userService;
	 

	 	@PostMapping("/create")
	    public ResponseEntity<Order> createOrder(@RequestBody Order order, Principal principal) {
	       order.setCustomer(userService.getUserByUsername(principal.getName()));
	       return ResponseEntity.ok(orderService.saveOrder(order));
	    }

}
