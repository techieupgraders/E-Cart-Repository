package com.dhanush.prod.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhanush.prod.dto.ReviewDto;
import com.dhanush.prod.service.ReviewService;

@RestController
@RequestMapping("/api/products")
public class RestControllerReview {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("/{id}/reviews")
	public ResponseEntity<ReviewDto> addReview(@PathVariable int id, @RequestBody ReviewDto reviewDto) {

		ReviewDto savedReview = reviewService.addReviewToProduct(id, reviewDto);

		return new ResponseEntity<ReviewDto>(savedReview, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}/reviews")
	public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable int id){
		List<ReviewDto> reviewDto = reviewService.getReviewsForProduct(id);
		return new ResponseEntity<List<ReviewDto>>(reviewDto,HttpStatus.OK);
	}
}
