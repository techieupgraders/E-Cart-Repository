package com.dhanush.order.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemLine implements Serializable {

	private static final long serialVersionUID = 1L;

	private Item item;

	private int qty;
}
