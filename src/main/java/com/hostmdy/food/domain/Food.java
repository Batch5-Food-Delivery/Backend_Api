package com.hostmdy.food.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
	private Long Id;
	private String name;
	private String picture;
	private Double price;
	
	@Column(columnDefinition = "text")
	private String description;

	private Boolean available;
	
}
