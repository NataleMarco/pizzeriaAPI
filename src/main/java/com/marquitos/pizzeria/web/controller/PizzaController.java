package com.marquitos.pizzeria.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;
import com.marquitos.pizzeria.service.PizzaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/pizza")
public class PizzaController{

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(this.pizzaService.getAll());
	}
	
	@GetMapping("/{idPizza}")
	public ResponseEntity<?> getPizzaById(@PathVariable int idPizza){
		return ResponseEntity.ok(this.pizzaService.getPizzaById(idPizza));
	}

	@PostMapping()
	public ResponseEntity<?> savePizza(@RequestBody PizzaEntity pizza) {
		return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
	}
	
	
}
