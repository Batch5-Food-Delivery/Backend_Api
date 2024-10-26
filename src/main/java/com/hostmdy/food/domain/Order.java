package com.hostmdy.food.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="orders")
public class Order {
	
	@Id
	@GeneratedValue(
	    strategy= GenerationType.AUTO,
	    generator="native"
	)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    private Restaruant restaurant;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
	
	@ManyToOne(fetch = FetchType.EAGER) // Unidirectional relationship
	@JoinColumn(name = "address_id") // Foreign key column
	private Address destination;
	
	private Double total;
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
		return "Order [id=" + id + ", restaurant=" + restaurant + ", customer=" + customer + ", destination="
				+ destination + ", total=" + total + ", completed=" + completed + ", startedAt=" + startedAt
				+ ", completedAt=" + completedAt + "]";
	}
	
	
}
