package com.dhanush.order.model;

import java.io.Serializable;

import org.redisson.api.RCascadeType;
import org.redisson.api.annotation.RCascade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

	private int id;
	private String name;
	private double price;

	@RCascade(RCascadeType.ALL)
	private double itemtotal;
}
