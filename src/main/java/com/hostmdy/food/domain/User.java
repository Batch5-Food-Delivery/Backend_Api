package com.hostmdy.food.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.hostmdy.food.domain.security.UserRoles;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	private String lastname;
	private String username;
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	private Boolean enable = true;
	private String profile;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<UserRoles> userRoles = new HashSet<>();
	
	// This field is only relavent for DRIVER role
	private Boolean available;
	
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@PrePersist
	private void onPersist() {
		this.createdAt = LocalDateTime.now();
		
	}
	
	@PreUpdate
	private void onUpdate() {
		this.updatedAt = LocalDateTime.now();
	}
	
	@JsonIgnore
	public boolean isAdmin() {
        return userRoles.stream()
                    .anyMatch(ur -> ur.getRole().getName().equals("ROLE_ADMIN"));
    }
	
	@JsonIgnore
	public boolean isDriver() {
        return userRoles.stream()
                    .anyMatch(ur -> ur.getRole().getName().equals("ROLE_DRIVER"));
    }
	
	public List<String> getRoles() {
		return userRoles.stream()
				.map(ur -> ur.getRole().getName()).toList();
	}
	
	public boolean isAvailable() {
		return this.available;
	}
	
}
