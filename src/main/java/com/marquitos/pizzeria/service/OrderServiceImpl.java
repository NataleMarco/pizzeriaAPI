package com.marquitos.pizzeria.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marquitos.pizzeria.persistence.entity.OrderEntity;
import com.marquitos.pizzeria.persistence.projection.OrderSummary;
import com.marquitos.pizzeria.persistence.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	private final String CARRYOUT = "C";
	private final String DELIVERY = "D";
	private final String ONSITE = "S";

	private OrderRepository orderRepository;

	private OrderServiceImpl(OrderRepository orderRepository){
		this.orderRepository = orderRepository;
	}

	public List<OrderEntity> getAll(){
		return this.orderRepository.findAll();
	}

	public List<OrderEntity> ordersFromCustomer(String idCustomer){
		return this.orderRepository.findOrdersFromCustomer(idCustomer);
	}

	public List<OrderEntity> getAfterToday(){
		LocalDateTime today = LocalDate.now().atTime(0,0);
		return this.orderRepository.findAllByDateAfter(today);
	}
	
	public List<OrderEntity> getOutsideOrders(){
		List<String> methods = Arrays.asList(CARRYOUT,DELIVERY);
		return this.orderRepository.findAllByMethodIn(methods);
	}
	
	public OrderEntity getOrderById(int idOrder){
		return this.orderRepository.findById(idOrder).orElse(null);
	}
	public OrderEntity saveOrder(OrderEntity order){
		return this.orderRepository.save(order);
	}
	public void deleteOrder(int idOrder){
		this.orderRepository.deleteById(idOrder);
	}
	public boolean exists(int idOrder){
		return this.orderRepository.existsById(idOrder);
	}

	public OrderSummary getSummary(int idOrder){
		return this.orderRepository.findSummary(idOrder);
	}

}
