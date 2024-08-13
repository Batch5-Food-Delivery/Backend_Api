package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.UserAddress;

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

}
