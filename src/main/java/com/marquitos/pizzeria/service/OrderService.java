package com.marquitos.pizzeria.service;

import java.util.List;

import com.marquitos.pizzeria.persistence.entity.OrderEntity;
import com.marquitos.pizzeria.persistence.projection.OrderSummary;

public interface OrderService{
	List<OrderEntity> getAll();
	OrderEntity getOrderById(int idOrder);
	OrderEntity saveOrder(OrderEntity order);
	void deleteOrder(int idOrder);
	boolean exists(int idOrder);
	List<OrderEntity> getAfterToday();
	List<OrderEntity> getOutsideOrders();
	List<OrderEntity> ordersFromCustomer(String idCustomer);
	OrderSummary getSummary(int idOrder);

}
