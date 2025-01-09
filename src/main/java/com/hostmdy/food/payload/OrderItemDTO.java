package com.hostmdy.food.payload;

import com.hostmdy.food.domain.Food;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDTO {

	private Food food;
	private int quantity;
}
