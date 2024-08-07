package com.hostmdy.food.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Restaruant {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String profile;
	private Boolean available;
	@Column(columnDefinition = "text")
	private String description;
}
