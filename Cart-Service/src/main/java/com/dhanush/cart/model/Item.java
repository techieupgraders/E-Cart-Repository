package com.dhanush.cart.model;

import java.io.Serializable;

import org.redisson.api.RCascadeType;
import org.redisson.api.annotation.RCascade;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

	private int id;
	private String name;
	private double price;

	@RCascade(RCascadeType.ALL)
	private double itemtotal;

}