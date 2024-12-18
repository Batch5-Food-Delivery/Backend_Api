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
import com.hostmdy.food.payload.OrderItemDTO;
import com.hostmdy.food.payload.OrderRequest;
import com.hostmdy.food.repository.DeliveryRepository;
import com.hostmdy.food.repository.OrderRepository;
import com.hostmdy.food.repository.UserAddressRepository;
import com.hostmdy.food.service.DeliveryService;
import com.hostmdy.food.service.FoodService;
import com.hostmdy.food.service.OrderItemService;
import com.hostmdy.food.service.OrderService;
import com.hostmdy.food.service.RestaruantService;
import com.hostmdy.food.service.UserService;

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
	private final DeliveryService deliveryService;

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
	public Order saveOrder(OrderRequest orderReq) {
		
		// Validate Restaurant
		Optional<Restaruant> restaurantOptional = restaurantService.getRestaruantById(orderReq.getRestaurant().getId());
		if (restaurantOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Your restaurant is not valid");
		}
		Restaruant restaurant = restaurantOptional.get();
		
		// Validate Address
		Optional<UserAddress> userAddressOptional = userAddressRepository
				.findByUserAndAddress(orderReq.getCustomer(), orderReq.getDestination());
		if (userAddressOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Address is not valid");
		}
		
		Order order = new Order();
		
		order.setCustomer(orderReq.getCustomer());
		order.setRestaurant(orderReq.getRestaurant());
		order.setDestination(orderReq.getDestination());
		order.setStartedAt(LocalDateTime.now());
		order.setCompleted(false);
		Order newOrder = orderRepository.save(order);
		
		Double total = 0.0;
		
		for (OrderItemDTO item: orderReq.getItems()) {
			Optional<Food> foodOptional = foodService.getFoodByIdAndRestaurant(item.getFood().getId(), restaurant);
			
			if(foodOptional.isEmpty()) {
				throw new DatabaseRecordNotFoundException("The food you ordered is not available at this restaurant");
			}
			
			Food food = foodOptional.get();
			OrderItem orderItem = new OrderItem();
			orderItem.setName(food.getName());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setPrice(food.getPrice());
			orderItem.setDiscount(food.getDiscount());
			orderItem.setOrder(newOrder);
			orderItemService.saveOrderItem(orderItem);
			
			total += (orderItem.getPrice() * (1 - orderItem.getDiscount() / 100)) * orderItem.getQuantity();
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

	@Override
	@Transactional
	public Order completeOrder(Long orderId, Long driverId) {
		// TODO Auto-generated method stub
		Optional<Order> orderOptional = orderRepository.findById(orderId);
		if (orderOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("Order not found");
		}
		
		Order order = orderOptional.get();
		order.setCompleted(true);
		deliveryService.createDelivery(order, driverId);
		
		System.out.println(order);
		return orderRepository.save(order);
	}

	@Override
	public Optional<Order> getOrderById(Long orderId) {
		// TODO Auto-generated method stub
		return orderRepository.findById(orderId);
	}

	@Override
	public List<Order> getOrdersByCustomer(User customer) {
		// TODO Auto-generated method stub
		return orderRepository.findByCustomer(customer);
	}
}
