package com.marquitos.pizzeria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marquitos.pizzeria.persistence.entity.CustomerEntity;
import com.marquitos.pizzeria.persistence.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository){
		this.customerRepository = customerRepository;
	}
	
	public List<CustomerEntity> findAll(){
		return this.customerRepository.findAll();
	}

	public CustomerEntity findByPhone(String phone){
		return this.customerRepository.findByPhone(phone);
	}
}
