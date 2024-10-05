package com.dhanush.price.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.price.service.PriceService;

@RestController
public class PriceController {

	@Autowired
	private PriceService priceservice;
	
	
	@GetMapping ("/api/price/{id}")
	public ResponseEntity<Double> getPriceById(@PathVariable int id) {
		Double price = priceservice.findPriceById(id);
		return new ResponseEntity<Double>(price,HttpStatus.OK);
	}
	
}
