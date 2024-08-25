package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.exception.DatabaseRecordNotFoundException;
import com.hostmdy.food.repository.DeliveryRepository;
import com.hostmdy.food.service.DeliveryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
	
	private final DeliveryRepository deliveryRepository;
	
	@Override
	public Optional<Delivery> getDeliveryById(Long id) {
		// TODO Auto-generated method stub
		return deliveryRepository.findById(id);
	}

	@Override
	public List<Delivery> getCompletedDeliveriesByDriver(User driver) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByDriverAndCompletedTrue(driver);
	}

	@Override
	public List<Delivery> getCurrentDeliveriesByDriver(User driver) {
		// TODO Auto-generated method stub
		return deliveryRepository.findByDriverAndCompletedFalse(driver);
	}

	@Override
	public Delivery completeDelivery(User driver, Long deliveryId) {
		// TODO Auto-generated method stub 
		
		Optional<Delivery> deliveryOptional = deliveryRepository.findByDriverAndId(driver, deliveryId);
		if (deliveryOptional.isEmpty()) {
			throw new DatabaseRecordNotFoundException("The delivery is not found");
		}
		
		Delivery delivery = deliveryOptional.get();
		delivery.setCompleted(true);
		return deliveryRepository.save(delivery);
	}

}
