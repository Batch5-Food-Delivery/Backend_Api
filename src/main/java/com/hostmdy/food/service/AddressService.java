package com.hostmdy.food.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.food.domain.Address;
import com.hostmdy.food.domain.User;

public interface AddressService {

	List<Address> myAddresses(User user);
	Address createAddress(Address address, User user);
}
