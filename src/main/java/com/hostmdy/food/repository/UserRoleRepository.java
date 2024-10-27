package com.hostmdy.food.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.food.domain.security.Role;
import com.hostmdy.food.domain.security.UserRoles;

public interface UserRoleRepository extends CrudRepository<UserRoles, Long> {

	List<UserRoles> findByRole(Role role);
}
