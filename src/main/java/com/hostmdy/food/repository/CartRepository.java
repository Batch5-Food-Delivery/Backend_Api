package com.hostmdy.food.repository;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Cart;

public interface CartRepository extends CrudRepository<Cart,Long>{

}
