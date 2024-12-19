package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.food.controller.RestaurantDeliveryController;
import com.hostmdy.food.domain.Menu;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.repository.MenuRepository;
import com.hostmdy.food.service.MenuService;
import com.hostmdy.food.service.RestaruantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
	
	private final RestaruantService restaurantService;
	private final MenuRepository menuRepository;

	@Override
	public List<Menu> getMenusByRestaurant(Long restaurantId) {
		// TODO Auto-generated method stub
		Optional<Restaruant> restaurantOptional = restaurantService.getRestaruantById(restaurantId);
		if (restaurantOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("No restaurant is found");
		}
		
		return menuRepository.findByRestaurant(restaurantOptional.get());
	}

	@Override
	public Menu createNewMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuRepository.save(menu);
	}

	@Transactional
	@Override
	public Menu updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		Optional<Menu> ogMenuOptional = menuRepository.findByIdAndRestaurant(menu.getId(), menu.getRestaurant());
		if (ogMenuOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Menu not found");
		}
		Menu ogMenu = ogMenuOptional.get();
		ogMenu.setName(menu.getName());
		return menuRepository.save(ogMenu);
	}

}
