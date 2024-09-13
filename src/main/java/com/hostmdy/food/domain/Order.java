package com.hostmdy.food.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	@GeneratedValue ( strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaruant restaurant;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id")
	private User customer;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
	
	@ManyToOne(fetch = FetchType.LAZY) // Unidirectional relationship
	@JoinColumn(name = "address_id") // Foreign key column
	private Address destination;
	
	
	
	
	private Double totalPrice;
	private boolean orderStatus;
	private LocalDateTime startedAt;
	private LocalDateTime completedAt;
	@Override
	public String toString() {
		return "Order [id=" + id + ", restaurant=" + restaurant + ", customer=" + customer + ", destination="
				+ destination + ", total=" + totalPrice + ", completed=" + orderStatus + ", startedAt=" + startedAt
				+ ", completedAt=" + completedAt + "]";
	}
	
	
}
