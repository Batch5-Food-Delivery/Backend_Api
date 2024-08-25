package com.hostmdy.food.service;

import java.util.List;

import com.hostmdy.food.domain.CartItem;

public interface CartItemService {
	
List<CartItem> getAllCartItemsByCart(Long cartId);
	
	CartItem saveCartItem(CartItem cartItem);
	
	CartItem addProductToCartItem(Long foodId,Long cartId);
	
	void deleteCartItem(Long cartItemId);


}
