package com.hostmdy.food.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hostmdy.food.domain.Address;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.domain.UserAddress;
import com.hostmdy.food.repository.AddressRepository;
import com.hostmdy.food.repository.UserAddressRepository;
import com.hostmdy.food.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;
	private final UserAddressRepository userAddressRepository;
	
	@Override
	public List<Address> myAddresses(User user) {
		// TODO Auto-generated method stub
		Optional<List<UserAddress>> userAddresses = userAddressRepository.findByUser(user);
		
		 return userAddresses.get().stream()
                 .map(UserAddress::getAddress) // Extract the Address from each UserAddress
                 .collect(Collectors.toList());
		
	}

	@Override
	public Address createAddress(Address address, User user) {
		// TODO Auto-generated method stub
		Address newAddress = addressRepository.save(address);
		UserAddress userAddress = new UserAddress();
		userAddress.setAddress(newAddress);
		userAddress.setUser(user);
		
		userAddressRepository.save(userAddress);
		
		return newAddress;
	}

}
