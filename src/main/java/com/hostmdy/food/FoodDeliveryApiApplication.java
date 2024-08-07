package com.hostmdy.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hostmdy.food.domain.Food;
import com.hostmdy.food.repository.FoodRepository;

@SpringBootApplication
public class FoodDeliveryApiApplication implements CommandLineRunner {
	
	@Autowired
	public FoodRepository foodRepository;

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Food food1 = new Food();
		
	}

}
