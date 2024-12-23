	package com.dhanush.price.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.price.service.PriceService;

@RestController
public class PriceController {
	
	private static final Logger logger = LoggerFactory.getLogger(PriceController.class);

	@Autowired
	private PriceService priceservice;
	
	
	@GetMapping ("/api/price/{id}")
	public ResponseEntity<Double> getPriceById(@RequestHeader("TRACKING_ID") String trackingId, @PathVariable int id) {
		logger.debug("getPriceById - Tracking ID found : "+trackingId);
		Double price = priceservice.findPriceById(id, trackingId);
		return new ResponseEntity<Double>(price,HttpStatus.OK);
	}
	
}
