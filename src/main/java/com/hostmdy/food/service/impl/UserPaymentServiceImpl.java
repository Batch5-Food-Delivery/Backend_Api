package com.hostmdy.food.service.impl;


import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.UserPayment;
import com.hostmdy.food.repository.UserPaymentRepository;
import com.hostmdy.food.service.UserPaymentService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserPaymentServiceImpl implements UserPaymentService {
	
	private final UserPaymentRepository userPaymentRepository;
	@Override
	public UserPayment createUserPayment(UserPayment userPayment) {
		// TODO Auto-generated method stub
		return userPaymentRepository.save(userPayment);
	}

}
