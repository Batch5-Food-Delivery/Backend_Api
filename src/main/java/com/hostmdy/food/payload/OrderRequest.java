package com.hostmdy.food.payload;

import java.util.List;

import com.hostmdy.food.domain.Address;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderRequest {
	
	private User customer;
	private Restaruant restaurant;
	private Address destination;
	List<OrderItemDTO> items;
}
