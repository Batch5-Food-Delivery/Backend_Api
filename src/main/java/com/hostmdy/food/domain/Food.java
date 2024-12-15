package com.hostmdy.food.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.hostmdy.food.controller.RestaurantDeliveryController;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
	@JsonIncludeProperties({"id"})
    private Menu menu;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
	@JsonIncludeProperties({"id"})
    private Restaruant restaurant;
	
	@OneToMany(mappedBy = "food")
	@JsonIgnore
	private List<CartItem> cartItems = new ArrayList<>();

	private Boolean available;
	
	
}
