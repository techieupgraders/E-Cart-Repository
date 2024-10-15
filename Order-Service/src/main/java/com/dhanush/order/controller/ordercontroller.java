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

	/*
	 * @Autowired private OrderRepositoryImpl orderrepo;
	 * 
	 * @Autowired private Order order;
	 * 
	 * @Autowired private ItemLine il;
	 */

	@Autowired
	private OrderService orderService;

	@PostMapping("/api/orders/{user}")
	public ResponseEntity<Order> retrieveOrder(@PathVariable String user) {
		Order order = orderService.retrieveOrder(user);
		return new ResponseEntity<Order>(order, HttpStatus.OK);
	}

	/*
	 * @PostMapping("/api/orders/{user}") public ResponseEntity<String>
	 * orderRetrieve(@PathVariable ("user") String user) throws IOException{ //
	 * order.setId(16); // order.setDate(LocalDateTime.now()); //
	 * order.setAmount(10000); // order.setUser("dhanush"); // return order;
	 * //return orderrepo.retrieve(user); //return orderrepo.findAll(user);
	 * //order.setId(user.); Optional<Order> c =
	 * orderrepo.getDetailsFromDatabase(user); if (c.isPresent()) { return
	 * ResponseEntity.status(HttpStatus.OK) .contentType(MediaType.APPLICATION_JSON)
	 * .body(c.get().toString()); //c.get().toJsonObject().toString() } else {
	 * return ResponseEntity.status(HttpStatus.NOT_FOUND)
	 * .contentType(MediaType.APPLICATION_JSON) .body(Json.createObjectBuilder()
	 * .add("message", "Order not found") .build() .toString()); }
	 * 
	 * }
	 */

}