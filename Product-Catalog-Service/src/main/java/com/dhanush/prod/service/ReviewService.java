package com.dhanush.prod.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.prod.dto.ProductDto;
import com.dhanush.prod.dto.ReviewDto;
import com.dhanush.prod.entity.Product;
import com.dhanush.prod.entity.Review;
import com.dhanush.prod.exception.ResourceNotFoundException;
import com.dhanush.prod.mapper.Mapper;
import com.dhanush.prod.repository.ProductRepo;
import com.dhanush.prod.repository.ReviewRepo;

@Service
public class ReviewService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	public ReviewDto addReviewToProduct(int id, ReviewDto reviewDto) {
		
		Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",String.valueOf(id)));
		
		Review newReview = new Review();
		newReview.setAuthor(reviewDto.getAuthor());
		newReview.setBody(reviewDto.getBody());
		newReview.setStars(reviewDto.getStars());
		newReview.setProduct(product);
		
		Review savedReview = reviewRepo.save(newReview);
		
		return Mapper.ReviewToReviewDto(savedReview);
	}

	public List<ReviewDto> getReviewsForProduct(int id) {
//		Product product = productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product","id",String.valueOf(id)));
		List<Review> reviews = reviewRepo.findByProductId(id);
		List<ReviewDto> allReviewsDto = new ArrayList<ReviewDto>();
		for(Review review: reviews) {
			ReviewDto reviewDto = Mapper.ReviewToReviewDto(review);
			allReviewsDto.add(reviewDto);
		}
		
		return allReviewsDto;
		
	}
}
