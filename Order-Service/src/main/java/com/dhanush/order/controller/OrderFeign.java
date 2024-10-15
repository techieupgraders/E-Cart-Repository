package com.dhanush.order.controller;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dhanush.order.model.ItemLine;

@FeignClient(url = "http://localhost:8083", name = "Cart-Service")
public interface OrderFeign {
	
	@GetMapping("/api/cart/{user}")
	public List<ItemLine> getCart(@PathVariable String user);
	
}