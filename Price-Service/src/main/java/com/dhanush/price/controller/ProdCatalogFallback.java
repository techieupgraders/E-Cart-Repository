package com.dhanush.price.controller;

import org.springframework.stereotype.Component;

@Component
public class ProdCatalogFallback implements PriceFeign{

	@Override
	public Double getPriceByProductId(int id, String trackingId) {
		return 0.0;
	}

	
}
