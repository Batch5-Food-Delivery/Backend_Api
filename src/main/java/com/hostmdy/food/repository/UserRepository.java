package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.User;

public interface UserRepository extends CrudRepository<User,Long>{

}
