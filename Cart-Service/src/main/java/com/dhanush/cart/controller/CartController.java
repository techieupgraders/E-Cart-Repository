package com.dhanush.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.cart.model.ItemLine;
import com.dhanush.cart.service.CartService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;

	@PostMapping("/api/cart/{user}")
	public ItemLine saveCart(@RequestBody ItemLine itemline, @PathVariable String user) {
		ItemLine saveItemLine = cartService.saveCart(itemline,user);
		return saveItemLine;	
	}
	
	@GetMapping("/api/cart/{user}")
	public List<ItemLine> getCart(@PathVariable String user){
		List<ItemLine> itemLineList = cartService.getItemLineForUser(user);
		return itemLineList;
	}
	
	@DeleteMapping("/api/cart/{user}")
	public ResponseEntity<Boolean> deleteCart(@PathVariable String user){
		Boolean deleteCartForUser = cartService.deleteCartForUser(user);
		return new ResponseEntity<Boolean>(deleteCartForUser,HttpStatus.OK);
	}
}