package com.dhanush.cart.model;

import java.io.Serializable;

import org.redisson.api.RCascadeType;
import org.redisson.api.annotation.RCascade;

public class Item implements Serializable {

	private int id;
	private String name;
	private double price;
	
	@RCascade(RCascadeType.ALL)
	private double itemtotal;
	
	
	public Item() {
		
	}

	public Item(int id, String name, double price, double itemtotal) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.itemtotal=itemtotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getItemtotal() {
		return itemtotal;
	}

	public void setItemtotal(double itemtotal) {
		this.itemtotal = itemtotal;
	}


}