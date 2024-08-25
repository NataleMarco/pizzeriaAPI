package com.marquitos.pizzeria.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer>{

}
