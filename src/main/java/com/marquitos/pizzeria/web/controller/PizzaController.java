package com.marquitos.pizzeria.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;
import com.marquitos.pizzeria.service.PizzaService;
import com.marquitos.pizzeria.service.dto.UpdatePizzaPriceDto;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Log4j2
@RestController
@RequestMapping("/pizza")
public class PizzaController{

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/vegan")
	public ResponseEntity<?> getVegan(){
		return ResponseEntity.ok(this.pizzaService.getVegan());
	}

	@GetMapping
	public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0")int page,
					@RequestParam(defaultValue = "5") int elements){
		return ResponseEntity.ok(this.pizzaService.getAll(page,elements));
	}

	@GetMapping("/available")
	public ResponseEntity<?> getAllAvailable(@RequestParam(defaultValue = "0") int page,
						 @RequestParam(defaultValue = "5") int elements,
						 @RequestParam(defaultValue = "price") String sortBy,
						 @RequestParam(defaultValue = "ASC") String sortDirection) {
		return ResponseEntity.ok(this.pizzaService.getAvailable(page,elements,sortBy,sortDirection));
	}


	@GetMapping("/cheapest")
	public ResponseEntity<?> getCheapest(){
		return ResponseEntity.ok(this.pizzaService.getCheapest());
	}

	@GetMapping("/expensive")
	public ResponseEntity<?> getExpensive(){
		return ResponseEntity.ok(this.pizzaService.getExpensive());
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name){
		return ResponseEntity.ok(this.pizzaService.getByName(name));
	}

	@GetMapping("/with/{ingredient}")
	public ResponseEntity<?> getWith(@PathVariable String ingredient){
		return ResponseEntity.ok(this.pizzaService.getWithIngredient(ingredient));
	}

	@GetMapping("/without/{ingredient}")
	public ResponseEntity<?> getWithout(@PathVariable String ingredient){
		return ResponseEntity.ok(this.pizzaService.getWithoutIngredient(ingredient));
	}


	@GetMapping("/{idPizza}")
	public ResponseEntity<?> getPizzaById(@PathVariable int idPizza){
		return ResponseEntity.ok(this.pizzaService.getPizzaById(idPizza));
	}

	@PostMapping()
	public ResponseEntity<?> savePizza(@RequestBody PizzaEntity pizza) {
		if(pizza.getIdPizza() == null || !this.pizzaService.exists(pizza.getIdPizza())){
			return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody PizzaEntity pizza) {
		if(pizza.getIdPizza() != null && this.pizzaService.exists(pizza.getIdPizza())){
			return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping("/price")
	public ResponseEntity<?> updatePrice(@RequestBody UpdatePizzaPriceDto dto) {
		log.info("Precio DTO = " + dto.getNewPrice());
		log.info("ID DTO = " + dto.getIdPizza());
		if(this.pizzaService.exists(dto.getIdPizza())){
			this.pizzaService.updatePizzaPrice(dto);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("{idPizza}")
	public ResponseEntity<?> delete(@PathVariable int idPizza){
		if(this.pizzaService.exists(idPizza)){
			this.pizzaService.deletePizza(idPizza);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

}
