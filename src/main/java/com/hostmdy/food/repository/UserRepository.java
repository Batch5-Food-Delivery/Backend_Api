package com.hostmdy.food.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	Optional<User> findByUsername(String username);
	
}
