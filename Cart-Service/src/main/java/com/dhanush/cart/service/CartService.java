package com.dhanush.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dhanush.cart.controller.pricefeign;
import com.dhanush.cart.model.ItemLine;
import com.dhanush.cart.repository.CarRepositoryImpl;

@Service
public class CartService {

	@Autowired
	private pricefeign pf;
	
	@Autowired
	private CarRepositoryImpl cart;

	public ItemLine saveCart(ItemLine itemline,String user) {
		Double feignprice = pf.getPriceById(itemline.getItem().getId());
		itemline.getItem().setPrice(feignprice);
		double tot = itemline.getItem().getPrice() * itemline.getQty();
		itemline.getItem().setItemtotal(tot);
		ItemLine itl = new ItemLine(itemline.getItem(), itemline.getQty());
		cart.save(user, itl);
		return itl;
	}
	
	public List<ItemLine> getItemLineForUser(String user){
		List<ItemLine> itemLineList = cart.findAll(user);
		return itemLineList;
	}

	public Boolean deleteCartForUser(String user) {
		Boolean clear = cart.clear(user);
		return clear;
	}
}