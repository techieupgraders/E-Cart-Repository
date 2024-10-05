package com.dhanush.prod.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dhanush.prod.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer>{

	List<Review> findByProductId(int id);
}
