package com.dhanush.cart.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (url= "http://localhost:8082" ,name="Price-Service")
public interface pricefeign {

	@GetMapping ("/api/price/{id}")
	public Double getPriceById(@PathVariable ("id") int id);
	
}