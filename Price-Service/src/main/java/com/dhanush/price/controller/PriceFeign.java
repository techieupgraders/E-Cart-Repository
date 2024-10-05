package com.dhanush.price.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8081", name = "Product-Catalog-Service")
public interface PriceFeign {
	
	@GetMapping("/api/price/{id}")
	public Double getPriceByProductId(@PathVariable int id);
	
}