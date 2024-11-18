package com.dhanush.price.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.price.controller.PriceController;
import com.dhanush.price.controller.PriceFeign;

@Service
public class PriceService {
	
	@Autowired
	private PriceFeign priceFeign;

	public Double findPriceById(int id, String trackingId) {
		Double priceByProductId = priceFeign.getPriceByProductId(id, trackingId);
		return priceByProductId;
	}

}
