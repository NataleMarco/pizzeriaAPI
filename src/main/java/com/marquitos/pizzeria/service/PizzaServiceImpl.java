package com.marquitos.pizzeria.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;
import com.marquitos.pizzeria.persistence.repository.PizzaPagSortRepository;
import com.marquitos.pizzeria.persistence.repository.PizzaRepository;
import com.marquitos.pizzeria.service.dto.UpdatePizzaPriceDto;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PizzaServiceImpl implements PizzaService{

	private final PizzaRepository pizzaRepository;
	private final PizzaPagSortRepository pizzaPagSortRepository;


	public PizzaServiceImpl(PizzaRepository pizzaRepository,PizzaPagSortRepository pizzaPagSortRepository){
		this.pizzaRepository = pizzaRepository;
		this.pizzaPagSortRepository = pizzaPagSortRepository;
	}

	public Page<PizzaEntity> getAll(int page, int elements) {
		Pageable pageRequest = PageRequest.of(page,elements);
		return this.pizzaPagSortRepository.findAll(pageRequest);
	}

	@Transactional
	public void updatePizzaPrice(UpdatePizzaPriceDto dto){
		this.pizzaRepository.updatePrice(dto);
	}

	public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
		Pageable pageRequest = PageRequest.of(page,elements,sort);
		return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
	}

	public List<PizzaEntity> getWithIngredient(String ingredient){
		return this.pizzaRepository.findByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
	}

	public List<PizzaEntity> getWithoutIngredient(String ingredient){
		return this.pizzaRepository.findByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
	}

	public List<PizzaEntity> getCheapest(){
		return this.pizzaRepository.findTop3ByAvailableTrueOrderByPrice();
	}

	public List<PizzaEntity> getExpensive(){
		return this.pizzaRepository.findTop3ByAvailableTrueOrderByPriceDesc();
	}

	public List<PizzaEntity> getVegan() {
		return this.pizzaRepository.findAllByVeganTrueAndAvailableTrue();
	}


	public PizzaEntity getByName(String name) {
		return this.pizzaRepository.findByAvailableTrueAndNameIgnoreCase(name);
	}
	

	public PizzaEntity getPizzaById(int id){
		return this.pizzaRepository.findById(id).orElse(null);
	}

	public PizzaEntity savePizza(PizzaEntity pizza){
		return this.pizzaRepository.save(pizza);

	}

	public void deletePizza(int idPizza){
		this.pizzaRepository.deleteById(idPizza);
	}


	public boolean exists(int idPizza){
		return this.pizzaRepository.existsById(idPizza);
	}
}
