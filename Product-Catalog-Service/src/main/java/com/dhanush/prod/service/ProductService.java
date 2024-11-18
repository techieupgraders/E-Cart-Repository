package com.dhanush.prod.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhanush.prod.dto.ProductDto;
import com.dhanush.prod.entity.Product;
import com.dhanush.prod.exception.DuplicateProductsNotAllowedException;
import com.dhanush.prod.exception.ResourceNotFoundException;
import com.dhanush.prod.mapper.Mapper;
import com.dhanush.prod.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public ProductDto saveProduct(ProductDto productDto) {
		Product savedProduct = null;
		Product product = Mapper.ProductDtoToProduct(productDto);
		if(productRepo.existsByNameAndPriceAndDescription(product.getName(), product.getPrice(), product.getDescription())) {
			throw new DuplicateProductsNotAllowedException("Both Description and price are duplicate under same Name");
		}else {
			savedProduct = productRepo.save(product);
		}
		return Mapper.ProductToProductDto(savedProduct);
	}

	public ProductDto getProductById(int id) {
		Product fetchedProduct = productRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));
		return Mapper.ProductToProductDto(fetchedProduct);
	}

	public List<ProductDto> saveMoreProducts(List<ProductDto> productDtos) {
		List<Product> products = new ArrayList<Product>();
		List<ProductDto> productsDto = new ArrayList<ProductDto>();

		for (ProductDto productDto : productDtos) {
			Product product = Mapper.ProductDtoToProduct(productDto);
			if (productRepo.existsByNameAndPriceAndDescription(product.getName(), product.getPrice(),
					product.getDescription())) {
				throw new DuplicateProductsNotAllowedException(
						"Both Description and price are duplicate under same Name");
			} else {
				products.add(product);
			}
		}

		List<Product> savedProducts = productRepo.saveAll(products);

		for (Product product : savedProducts) {
			ProductDto productDto = Mapper.ProductToProductDto(product);
			productsDto.add(productDto);
		}

		return productsDto;
	}

	public List<ProductDto> getAllProducts() {
		List<Product> allProducts = productRepo.findAll();
		List<ProductDto> allProductsDto = new ArrayList<ProductDto>();
		for (Product product : allProducts) {
			ProductDto productDto = Mapper.ProductToProductDto(product);
			allProductsDto.add(productDto);
		}
		return allProductsDto;
	}

	public List<ProductDto> getProductsWithCharacter(char character) {
		List<Product> productsByCharacter = productRepo.findByNameContainingIgnoreCase(character);
		List<ProductDto> allProductsDto = new ArrayList<ProductDto>();
		for (Product product : productsByCharacter) {
			ProductDto productDto = Mapper.ProductToProductDto(product);
			allProductsDto.add(productDto);
		}
		return allProductsDto;
	}

	public Double getPriceByProductId(int id) {
		
		Double price = productRepo.getPriceByProductId(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "id", String.valueOf(id)));

		return price;
	}

	/*
	 * public static Example<Product> checkForDuplicateProduct(Product product) { //
	 * checks if name and price and description are exact match ExampleMatcher
	 * modelMatcher = ExampleMatcher.matching().withIgnorePaths("id")
	 * .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact())
	 * .withMatcher("price", ExampleMatcher.GenericPropertyMatchers.exact())
	 * .withMatcher("description", ExampleMatcher.GenericPropertyMatchers.exact());
	 * 
	 * Example<Product> example = Example.of(product, modelMatcher);
	 * 
	 * return example; }
	 */

}
