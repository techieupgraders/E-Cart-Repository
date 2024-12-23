package com.dhanush.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.order.controller.OrderFeign;
import com.dhanush.order.model.ItemLine;
import com.dhanush.order.model.Order;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class OrderService {

	@Autowired
	private OrderFeign orderFeign;

	@CircuitBreaker(name = "orderService", fallbackMethod = "fallbackretrieveOrder")
	public Order retrieveOrder(String user) {
		List<ItemLine> cart = orderFeign.getCart(user);
		Double totalAmount = 0.0;
		Order order = new Order();
		order.setId(1000000000 + new Random().nextInt(900000000));
		for (ItemLine itemLine : cart) {
			totalAmount = itemLine.getItem().getItemtotal() + totalAmount;
		}
		order.setAmount(totalAmount);
		order.setDate(LocalDateTime.now());
		order.setUser(user);

		return order;
	}
	
	public Order fallbackretrieveOrder(String user,Throwable throwable) {
		Order dummyOrder = new Order();
		dummyOrder.setId(0);
		dummyOrder.setAmount(0);
		dummyOrder.setDate(LocalDateTime.now());
		dummyOrder.setUser("Fallback User");
		return dummyOrder;
	}

}
