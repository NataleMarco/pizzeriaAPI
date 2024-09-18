package com.marquitos.pizzeria.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
@Setter
@Getter
@NoArgsConstructor
public class OrderItemEntity {
	@Id
	@Column(nullable = false, name = "id_order" )
	private Integer idOrder;

	@Id
	@Column(nullable = false, name = "id_item" )
	private Integer idItem;

	@Column(nullable = false, name = "id_pizza")
	private Integer idPizza;

	@Column(nullable = false, columnDefinition = "DECIMAL(2,1)")
	private Double quantity;

	@Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
	private Double price;

	@ManyToOne
	@JoinColumn(name = "id_order", referencedColumnName = "id_order", insertable = false, updatable = false)
	@JsonIgnore
	private OrderEntity order;

	@OneToOne
	@JoinColumn(name = "id_pizza", referencedColumnName = "id_pizza", insertable = false, updatable = false)
	private PizzaEntity pizza;

}
