package com.marquitos.pizzeria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marquitos.pizzeria.service.CustomerService;
import com.marquitos.pizzeria.service.OrderService;



@RestController
@RequestMapping("/customers")
public class CustomerController{

	@Autowired
	private CustomerService customerService;


	@Autowired
	private OrderService orderService; 

	@GetMapping("/phone/{phone}")
	public ResponseEntity<?> findByPhone(@PathVariable String phone){
		return ResponseEntity.ok(this.customerService.findByPhone(phone));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok(this.customerService.findAll());
	}


	@Secured("ROLE_ADMIN")
	@GetMapping("/customer/{idCustomer}")
	public ResponseEntity<?> getCustomerOrders(@PathVariable String idCustomer) {
		return ResponseEntity.ok(this.orderService.ordersFromCustomer(idCustomer));
	}
	
}
