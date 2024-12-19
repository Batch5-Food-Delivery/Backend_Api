package com.hostmdy.food.service;

import java.util.List;

import com.hostmdy.food.domain.Menu;

public interface MenuService {
	List<Menu> getMenusByRestaurant(Long restaurantId);
	Menu createNewMenu(Menu menu);
	Menu updateMenu(Menu menu);
}
