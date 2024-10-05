package com.dhanush.prod.dto;

import com.dhanush.prod.entity.Product;

import lombok.Data;

@Data
public class ReviewDto {

	private int reviewId;
	private int stars;
	private String author;
	private String body;
	private Product product;

}
