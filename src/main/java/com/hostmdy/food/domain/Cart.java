package com.hostmdy.food.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double grandTotal = 0.0;
	
	@OneToMany(mappedBy = "cart")
	@JsonIgnore
	private List<CartItem> cartItems = new ArrayList<>();
	
	@OneToOne(mappedBy = "cart")
	@JsonIgnore
	private User user;
	
}
