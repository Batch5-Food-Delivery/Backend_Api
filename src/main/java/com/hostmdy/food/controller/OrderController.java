package com.hostmdy.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Order;
import com.hostmdy.food.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	 @Autowired
	    private OrderService orderService;

	    @PostMapping("/create")
	    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
	        Order savedOrder = orderService.saveOrder(order);
	        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
	    }

}
