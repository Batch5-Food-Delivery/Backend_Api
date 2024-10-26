package com.hostmdy.food.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @Getter @Setter
public class OrderCompleteRequest {

	private Long orderId;
	private Long driverId;
}
