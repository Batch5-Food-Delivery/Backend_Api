package com.hostmdy.food.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Optional<Role> findByName(String name);
}
