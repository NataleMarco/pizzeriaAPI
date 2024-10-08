package com.marquitos.pizzeria.persistence.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "pizza_order")
public class OrderEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order",nullable = false)
	private Integer idOrder;

	@Column(nullable = false, name = "id_customer", length = 15)
	private String idCustomer;

	@Column(nullable = false, columnDefinition = "DATETIME")
	private LocalDateTime date;

	@Column(nullable = false, columnDefinition = "DECIMAL(6,2)")
	private Double total;

	@Column(nullable = false,length = 1,columnDefinition = "CHAR(1)")
	private Character method;

	@Column(name = "additional_notes",length = 200)
	private String additionalNotes;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_customer", referencedColumnName = "id_customer", insertable = false, updatable = false)
	@JsonIgnore
	private CustomerEntity customer;

	@OneToMany(mappedBy = "order")
	private List<OrderItemEntity> items;
}
