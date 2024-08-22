package com.hostmdy.food.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hostmdy.food.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class UserRoles {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	@JsonIgnore
	private Role role;

	public UserRoles(User user, Role role) {
		super();
		this.user = user;
		this.role = role;
	}
	
	
}
