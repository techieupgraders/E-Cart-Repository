package com.dhanush.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.cart.model.ItemLine;
import com.dhanush.cart.service.CartService;

@RestController
public class CartController {
	
	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Autowired
	private CartService cartService;

	@PostMapping("/api/cart/{user}")
	public String saveCart(@RequestHeader("TRACKING_ID") String trackingId,@RequestBody ItemLine itemline, @PathVariable String user) {
		logger.debug("saveCart - Tracking ID found : "+trackingId);
		ResponseEntity<?> cartResponse = cartService.saveCart(trackingId,itemline,user);
		if(cartResponse.getBody() instanceof ItemLine) {
			String response = "Item Saved. Total Amount for the product : "+((ItemLine) cartResponse.getBody()).getItem().getName()+" is "+((ItemLine) cartResponse.getBody()).getItem().getItemtotal();
		return response;
		}else {
			return (String) cartResponse.getBody();
		}
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
	
	@GetMapping("/api/cart/total/{user}")
	public ResponseEntity<?> getTotalAmountForUser(
	@RequestHeader(value="Caller-Service",required = false) String callerService,
	@PathVariable String user){
		Double totalAmountForUser = cartService.getTotalAmountForUser(user);
		String response = "Total Cart Amount for the User : "+user+" is "+totalAmountForUser;
		if("getDoubleAmount".equalsIgnoreCase(callerService)) {
			return ResponseEntity.ok(totalAmountForUser);
		}else {
			return ResponseEntity.ok(response);
		}
	}
}