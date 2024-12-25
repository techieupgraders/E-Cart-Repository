package com.dhanush.cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	public ResponseEntity<?> saveCart(String trackingId, ItemLine itemline, String user) {
		
		Optional<ItemLine> yesOrNoProduct = cart.findById(itemline.getItem().getId(), user);
		
		Double feignprice = pf.getPriceById(trackingId, itemline.getItem().getId());
		
		if(feignprice!=0.0) {
			itemline.getItem().setPrice(feignprice);

			if (yesOrNoProduct.isPresent()) {
				double tot = itemline.getItem().getPrice() * (itemline.getQty() + yesOrNoProduct.get().getQty());
				ItemLine itl = extracted(itemline, user, tot, itemline.getQty() + yesOrNoProduct.get().getQty());
				return ResponseEntity.ok(itl);
			} else {
				double tot = itemline.getItem().getPrice() * itemline.getQty();
				ItemLine itl = extracted(itemline, user, tot, itemline.getQty());
				return ResponseEntity.ok(itl);
			}
		}else {
			return ResponseEntity.ok("FallBack Triggered in Price Service. Product not added to cart");
		}
	}

	private ItemLine extracted(ItemLine itemline, String user, double tot, int qty) {
		itemline.getItem().setItemtotal(tot);
		ItemLine itl = new ItemLine(itemline.getItem(), qty);
		cart.save(user, itl);
		return itl;
	}

	public List<ItemLine> getItemLineForUser(String user) {
		List<ItemLine> itemLineList = cart.findAll(user);
		return itemLineList;
	}

	public Boolean deleteCartForUser(String user) {
		Boolean clear = cart.clear(user);
		return clear;
	}
	
	public Double getTotalAmountForUser(String user) {
		Double total = cart.findTotalAmount(user);
		System.out.println(total);
		return total;
	}
}