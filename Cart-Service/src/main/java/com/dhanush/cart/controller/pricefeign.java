package com.dhanush.cart.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient (name="Price-Service")
public interface pricefeign {

	@GetMapping ("/api/price/{id}")
	public Double getPriceById(@RequestHeader("TRACKING_ID") String trackingId, @PathVariable ("id") int id);
	
}