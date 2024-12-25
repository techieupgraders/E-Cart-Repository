package com.dhanush.order.controller;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.dhanush.order.model.ItemLine;

@FeignClient(name = "Cart-Service")
public interface OrderFeign {
	
//	@GetMapping("/api/cart/{user}")
//	public List<ItemLine> getCart(@PathVariable String user);
	
	@GetMapping("/api/cart/total/{user}")
	public String getTotalAmountForUser(@RequestHeader("Caller-Service") String callerService, @PathVariable String user);
	
}