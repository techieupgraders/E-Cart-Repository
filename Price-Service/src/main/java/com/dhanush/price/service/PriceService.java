package com.dhanush.price.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.price.controller.PriceFeign;

@Service
public class PriceService {
	
	@Autowired
	private PriceFeign priceFeign;

	public Double findPriceById(int id) {
		Double priceByProductId = priceFeign.getPriceByProductId(id);
		return priceByProductId;
	}

}
