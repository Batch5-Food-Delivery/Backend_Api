package com.hostmdy.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public interface RestaruantRepository extends CrudRepository<Restaruant, Long> {

	Optional<Restaruant> findByIdAndOwner(Long id, User owner);
	List<Restaruant> findByAvailableTrue();
}
