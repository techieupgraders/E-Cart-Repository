package com.dhanush.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.cart.model.ItemLine;
import com.dhanush.cart.repository.CarRepositoryImpl;
import com.dhanush.cart.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	@PostMapping("/api/cart/{user}")
	public ItemLine saveCart(@RequestBody ItemLine itemline, @RequestParam String user) {
		ItemLine saveItemLine = cartService.saveCart(itemline,user);
		return saveItemLine;
		
	}
}