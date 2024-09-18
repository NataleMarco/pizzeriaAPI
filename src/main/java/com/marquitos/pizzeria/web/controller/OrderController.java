package com.marquitos.pizzeria.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marquitos.pizzeria.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/orders")
public class OrderController{

	@Autowired
	private OrderService orderService;

	@GetMapping()
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.orderService.getAll());
	}

	@GetMapping("/customer/{idCustomer}")
	public ResponseEntity<?> ordersFromCustomer(@PathVariable String idCustomer) {
		return ResponseEntity.ok(this.orderService.ordersFromCustomer(idCustomer));
	}

	@GetMapping("/today")
	public ResponseEntity<?> getNewOrders() {
		return ResponseEntity.ok(this.orderService.getAfterToday());
	}

	@GetMapping("/outside")
	public ResponseEntity<?> getOutsideOrders() {
		return ResponseEntity.ok(this.orderService.getOutsideOrders());
	}

	@GetMapping("/summary/{id}")
	public ResponseEntity<?> getSummary(@PathVariable int id) {
		return ResponseEntity.ok(this.orderService.getSummary(id));
	}
	


}
