package com.hostmdy.food.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Cart;
import com.hostmdy.food.domain.CartItem;

public interface CartItemRepository extends CrudRepository<CartItem,Long>{
	
	List<CartItem> findByCart(Cart cart);

}
