package com.hostmdy.food.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hostmdy.food.serializer.DeliverySerializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@JsonSerialize(using = DeliverySerializer.class)
public class Delivery {

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaruant restaurant;
	
	@ManyToOne(fetch = FetchType.LAZY) // Unidirectional relationship
	@JoinColumn(name = "restaurant_address_id") // Foreign key column
	private Address restaurantAddress;
	
	 @OneToOne(cascade = CascadeType.ALL)
	 @JoinColumn(name = "order_id", referencedColumnName = "id")
	 private Order order;
	 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "driver_id")
	private User driver;
	
	@ManyToOne(fetch = FetchType.LAZY) // Unidirectional relationship
	@JoinColumn(name = "customer_address_id") // Foreign key column
	private Address destination;
	
	private boolean completed;
	private LocalDateTime startedAt;
	private LocalDateTime completedAt;
	
	@PrePersist
	private void onPersist() {
		this.startedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	private void onUpdate() {
		this.completedAt = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		return "Delivery [id=" + id + ", restaurant=" + restaurant.getAddress() + ", restaurantAddress=" + restaurantAddress
				+ ", order=" + order + ", customer=" + customer + ", driver=" + driver + ", destination=" + destination
				+ ", completed=" + completed + ", startedAt=" + startedAt + ", completedAt=" + completedAt + "]";
	}
	
	
}
