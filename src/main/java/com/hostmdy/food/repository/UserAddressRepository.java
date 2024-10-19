package com.hostmdy.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Address;
import com.hostmdy.food.domain.User;
import com.hostmdy.food.domain.UserAddress;

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

	Optional<List<UserAddress>> findByUser (User user);
	Optional<UserAddress> findByUserAndAddress(User user, Address address);
}
