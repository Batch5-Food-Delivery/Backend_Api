package com.hostmdy.food.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hostmdy.food.serializer.AddressSerializer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonSerialize(using = AddressSerializer.class)
public class Address {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	private String township;
	private String street;
	private String additionalDetails;
	@Override
	public String toString() {
		return "Address [Id=" + id + ", township=" + township + ", street=" + street + ", additionalDetails="
				+ additionalDetails + "]";
	}
	
	
}
