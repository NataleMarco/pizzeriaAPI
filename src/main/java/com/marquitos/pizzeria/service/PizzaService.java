package com.marquitos.pizzeria.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;
import com.marquitos.pizzeria.service.dto.UpdatePizzaPriceDto;

public interface PizzaService {
	Page<PizzaEntity> getAll(int page, int elements);
	Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection);
	PizzaEntity getPizzaById(int idPizza);
	PizzaEntity savePizza(PizzaEntity pizza);
	boolean exists(int idPizza);
	void deletePizza(int idPizza);
	PizzaEntity getByName(String name);
	List<PizzaEntity> getWithIngredient(String ingredient);
	List<PizzaEntity> getWithoutIngredient(String ingredient);
	List<PizzaEntity> getVegan();
	List<PizzaEntity> getCheapest();
	List<PizzaEntity> getExpensive();
	void updatePizzaPrice(UpdatePizzaPriceDto pizza);
}

