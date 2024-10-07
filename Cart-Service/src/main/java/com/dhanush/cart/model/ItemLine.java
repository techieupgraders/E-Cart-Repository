package com.dhanush.cart.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemLine implements Serializable {

	private static final long serialVersionUID = 1L;

	private Item item;

	private int qty;

}