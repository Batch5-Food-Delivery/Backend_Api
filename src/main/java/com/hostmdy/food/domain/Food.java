package com.hostmdy.food.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Food {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String picture;
	private Double price;
	
	@Column(columnDefinition = "text")
	private String description;
	private String category;
	private Double discount;
	
	@OneToMany(mappedBy = "food")
	@JsonIgnore
	private List<CartItem> cartItems = new ArrayList<>();

	private Boolean available;
	
	
}
