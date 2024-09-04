package com.hostmdy.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.hostmdy.food.domain.Food;
import com.hostmdy.food.domain.Region;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.repository.FoodRepository;
import com.hostmdy.food.repository.OrderRepository;
import com.hostmdy.food.repository.RegionRepository;
import com.hostmdy.food.repository.RestaruantRepository;

@SpringBootApplication
public class FoodDeliveryApiApplication implements CommandLineRunner {
	
	@Autowired
	public FoodRepository foodRepository;
	@Autowired
	public RegionRepository regionRepository;
	@Autowired
	public RestaruantRepository resRepository;

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Food food1 = new Food();
		food1.setAvailable(true);
		food1.setDescription("This is Test");
		food1.setName("test");
		food1.setPicture("test");
		food1.setPrice(900.3);
		//foodRepository.save(food1);
		
		
		Restaruant rest1 = new Restaruant();
		rest1.setAvailable(true);
		rest1.setDescription("This is Test");
		rest1.setName("Test");
		rest1.setProfile("This is test profile");
		resRepository.save(rest1);
		
		Region reg1 = new Region();
		reg1.setRegion("Mandalay");
		regionRepository.save(reg1);
		
	}

}
