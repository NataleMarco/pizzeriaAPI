package com.marquitos.pizzeria.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import com.marquitos.pizzeria.persistence.entity.PizzaEntity;
import com.marquitos.pizzeria.service.dto.UpdatePizzaPriceDto;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer>{
	List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
	PizzaEntity findByAvailableTrueAndNameIgnoreCase(String name);
	List<PizzaEntity> findByAvailableTrueAndDescriptionContainingIgnoreCase(String ingredient);
	List<PizzaEntity> findByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);
	List<PizzaEntity> findAllByVeganTrueAndAvailableTrue();
	List<PizzaEntity> findTop3ByAvailableTrueOrderByPrice();
	List<PizzaEntity> findTop3ByAvailableTrueOrderByPriceDesc();

	@Query(value = 
		"UPDATE pizza " + 
		"SET price = :#{#newPizzaPrice.getNewPrice} " + 
		"WHERE id_pizza = :#{#newPizzaPrice.getIdPizza} ",nativeQuery = true)
	@Modifying
	void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}
