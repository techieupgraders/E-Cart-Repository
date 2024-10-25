package com.dhanush.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//import com.dhanush.product.model.Item;
//import com.dhanush.product.model.ItemLine;
//import com.dhanush.product.model.ItemTotal;
import com.dhanush.order.model.Order;
import com.dhanush.order.service.OrderService;

@RestController
public class ordercontroller {

	@Autowired
	private OrderService orderService;

	@PostMapping("/api/orders/{user}")
	public ResponseEntity<Order> retrieveOrder(@PathVariable String user) {
		Order order = orderService.retrieveOrder(user);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

}