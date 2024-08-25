package com.marquitos.pizzeria.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity{
	@Id
	@Column(nullable = false,name = "id_customer",length = 15)
	private String idCustomer;

	@Column(nullable = false, length = 60)
	private String name;

	@Column(nullable = false, length = 100)
	private String address;

	@Column(nullable = false, length = 50)
	private String email;

	@Column(nullable = false, length = 20,name = "phone_number")
	private String phoneNumber;

}
