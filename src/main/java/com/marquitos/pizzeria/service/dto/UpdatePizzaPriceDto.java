package com.marquitos.pizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
	private double newPrice;
	private int idPizza;

	public void watafak(){ 
		this.getIdPizza();
		this.getNewPrice();
	}
}
