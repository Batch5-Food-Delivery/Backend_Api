package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Address;

public interface AddressRepository extends CrudRepository<Address, Long> {

}
