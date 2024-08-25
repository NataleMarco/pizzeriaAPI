package com.marquitos.pizzeria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;
import com.marquitos.pizzeria.persistence.repository.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService{

	private final PizzaRepository pizzaRepository;

	public PizzaServiceImpl(PizzaRepository pizzaRepository){
		this.pizzaRepository = pizzaRepository;
	}

	@Override
	public List<PizzaEntity> getAll() {
		return this.pizzaRepository.findAll();
	}

	@Override
	public PizzaEntity getPizzaById(int id){
		return this.pizzaRepository.findById(id).orElse(null);
	}

	@Override
	public PizzaEntity savePizza(PizzaEntity pizza){
		return this.pizzaRepository.save(pizza);

	}
}
