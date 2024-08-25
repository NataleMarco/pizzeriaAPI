package com.marquitos.pizzeria.service;

import java.util.List;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;

public interface PizzaService {
	List<PizzaEntity> getAll();
	PizzaEntity getPizzaById(int id);
	PizzaEntity savePizza(PizzaEntity pizza);
}
