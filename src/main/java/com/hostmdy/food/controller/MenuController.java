package com.hostmdy.food.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hostmdy.food.domain.Menu;
import com.hostmdy.food.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@CrossOrigin("http://localhost3000")
public class MenuController {

	private final MenuService menuService;
	
	@GetMapping("/{restaurantId}")
	public ResponseEntity<List<Menu>> getallMenusFromRestaurant(@PathVariable Long restaurantId) {
		List<Menu> menus = menuService.getMenusByRestaurant(restaurantId);
		
		return ResponseEntity.ok(menus);
	}
	
}
