package com.hostmdy.food.domain.security;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Role {
	
	@Id
	private Integer id;
	
	private String name;
	
	@OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UserRoles> userRoles = new HashSet<>();
	
}	
