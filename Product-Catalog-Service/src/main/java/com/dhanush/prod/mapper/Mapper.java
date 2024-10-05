package com.dhanush.prod.mapper;

import com.dhanush.prod.dto.ProductDto;
import com.dhanush.prod.dto.ReviewDto;
import com.dhanush.prod.entity.Product;
import com.dhanush.prod.entity.Review;

public class Mapper {

	public static ProductDto ProductToProductDto(Product product) {
		ProductDto dto = new ProductDto();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setDescription(product.getDescription());
		dto.setPrice(product.getPrice());

		return dto;
	}
	
	public static Product ProductDtoToProduct(ProductDto productdto) {
		Product product = new Product();
		product.setId(productdto.getId());
		product.setName(productdto.getName());
		product.setDescription(productdto.getDescription());
		product.setPrice(productdto.getPrice());

		return product;
	}
	
	public static Review ReviewDtoToReview(ReviewDto reviewDto) {
		Review review = new Review();
		review.setReviewId(reviewDto.getReviewId());
		review.setAuthor(reviewDto.getAuthor());
		review.setBody(reviewDto.getBody());
		review.setStars(reviewDto.getStars());
		
		return review;
	}
	
	public static ReviewDto ReviewToReviewDto(Review review) {
		ReviewDto reviewDto = new ReviewDto();
		reviewDto.setReviewId(review.getReviewId());
		reviewDto.setAuthor(review.getAuthor());
		reviewDto.setBody(review.getBody());
		reviewDto.setStars(review.getStars());
		
		reviewDto.setProduct(review.getProduct());
		
		return reviewDto;
	}
}
