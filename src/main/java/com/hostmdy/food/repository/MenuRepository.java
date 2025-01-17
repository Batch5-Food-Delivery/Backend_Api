package com.hostmdy.food.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.Menu;
import com.hostmdy.food.domain.Restaruant;

public interface MenuRepository extends CrudRepository<Menu, Long>{
 List<Menu> findByRestaurant(Restaruant restaurant);
 Optional<Menu> findByIdAndRestaurant(Long menuId, Restaruant restaurant);
}
