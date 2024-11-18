package com.dhanush.price.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.dhanush.price.config.FeignConfig;

@FeignClient(name = "Product-Catalog-Service", configuration = FeignConfig.class)
public interface PriceFeign {
	
	@GetMapping("/api/price/{id}")
	public Double getPriceByProductId(@PathVariable int id, @RequestHeader("TRACKING_ID") String trackingId);
	
}