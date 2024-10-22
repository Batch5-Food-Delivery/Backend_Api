package com.hostmdy.food.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.domain.Order;
import com.hostmdy.food.domain.OrderItem;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.domain.UserAddress;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.repository.DeliveryRepository;
import com.hostmdy.food.repository.OrderRepository;
import com.hostmdy.food.repository.UserAddressRepository;
import com.hostmdy.food.service.FoodService;
import com.hostmdy.food.service.OrderItemService;
import com.hostmdy.food.service.OrderService;
import com.hostmdy.food.service.RestaruantService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepository;
	private final UserAddressRepository userAddressRepository;
	private final RestaruantService restaurantService;
	private final FoodService foodService;
	private final OrderItemService orderItemService;

	@Override
	public Order getOrderByRestaurantAndId(Restaruant restaurant, Long OrderId) {
		
		Optional<Order> order = orderRepository.findByRestaurantAndId(restaurant, OrderId);
		if (order.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Your restaurant doesn't have this order");
		}
		
		return order.get();
	}
	
	@Override
	@Transactional
	public Order saveOrder(Order order) {
		
		// Validate Restaurant
		Optional<Restaruant> restaurantOptional = restaurantService.getRestaruantById(order.getRestaurant().getId());
		if (restaurantOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Your restaurant is not valid");
		}
		Restaruant restaurant = restaurantOptional.get();
		
		// Validate Address
		Optional<UserAddress> userAddressOptional = userAddressRepository
				.findByUserAndAddress(order.getCustomer(), order.getDestination());
		if (userAddressOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Address is not valid");
		}
		
		order.setStartedAt(LocalDateTime.now());
		order.setCompleted(false);
		Order newOrder = orderRepository.save(order);
		
		Double total = 0.0;
		
		for (OrderItem item: newOrder.getItems()) {
			Optional<Food> foodOptional = foodService.getFoodByIdAndRestaurant(item.getFood().getId(), restaurant);
			
			if(foodOptional.isEmpty()) {
				throw new DatabaseRecordNotFoundException("The food you ordered is not available at this restaurant");
			}
			
			Food food = foodOptional.get();
			item.setPrice(food.getPrice());
			item.setDiscount(food.getDiscount());
			item.setOrder(newOrder);
			orderItemService.saveOrderItem(item);
			
			total += (item.getPrice() * (1 - item.getDiscount() / 100)) * item.getQuantity();
		}
		
		newOrder.setTotal(total);
		return orderRepository.save(newOrder);
		
		
	}

	@Override
	public List<Order> getCurrentOrdersByRestaurant(Restaruant restaurant) {
		// TODO Auto-generated method stub
		return orderRepository.findByRestaurantAndCompletedFalse(restaurant);
	}

	@Override
	public List<Order> getCompletedOrdersByRestaurant(Restaruant restaurant) {
		// TODO Auto-generated method stub
		return orderRepository.findByRestaurantAndCompletedTrue(restaurant);
	}
}
