package com.marquitos.pizzeria.service;

import java.util.List;

import com.marquitos.pizzeria.persistence.entity.CustomerEntity;

public interface CustomerService {
	CustomerEntity findByPhone(String phone);
	List<CustomerEntity> findAll();
}
