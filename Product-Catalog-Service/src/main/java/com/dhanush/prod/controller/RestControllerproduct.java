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

import com.dhanush.prod.dto.ProductDto;
import com.dhanush.prod.service.ProductService;

@RestController
public class RestControllerproduct {

	@Autowired
	private ProductService productService;

	@PostMapping("/api/products/")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productdto) {

		ProductDto savedProduct = productService.saveProduct(productdto);

		return new ResponseEntity<ProductDto>(savedProduct, HttpStatus.CREATED);
	}

	@GetMapping("/api/products/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable int id) {
		ProductDto fetchedProduct = productService.getProductById(id);

		return new ResponseEntity<ProductDto>(fetchedProduct, HttpStatus.OK);
	}
	
	@PostMapping("/api/products/all")
	public ResponseEntity<List<ProductDto>> addMoreProducts(@RequestBody List<ProductDto> productDto) {
		//TODO: process POST request
		List<ProductDto> savedProducts = productService.saveMoreProducts(productDto);
		return new ResponseEntity<List<ProductDto>>(savedProducts, HttpStatus.OK);
	}
	
	@GetMapping("/api/products/")
	public ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> allProducts = productService.getAllProducts();
		return new ResponseEntity<List<ProductDto>>(allProducts,HttpStatus.OK);
	}
	
	@GetMapping("/api/products/byName/{character}")
	public ResponseEntity<List<ProductDto>> getAllProductsByCharacter(@PathVariable char character){
		List<ProductDto> productsWithCharacter = productService.getProductsWithCharacter(character);
		return new ResponseEntity<List<ProductDto>>(productsWithCharacter,HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/price/{id}")
	public Double getPriceByProductId(@PathVariable int id) {
		Double price =productService.getPriceByProductId(id);
		return price;
	}
}
