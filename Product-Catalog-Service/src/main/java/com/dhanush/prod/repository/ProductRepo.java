package com.dhanush.prod.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dhanush.prod.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	public boolean existsByNameAndPriceAndDescription(String name,double price, String description);
	

	List<Product> findByNameContainingIgnoreCase(char letter);
	
	@Query(nativeQuery = true,value="select p.price from Product p where p.id=:id")
	Optional<Double> getPriceByProductId(int id);
}
