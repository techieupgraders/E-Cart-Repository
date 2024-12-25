package com.dhanush.cart.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.dhanush.cart.model.ItemLine;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Resource;

@Repository
public class CarRepositoryImpl {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> template;
	
	private final ObjectMapper objectMapper = new ObjectMapper();

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, ItemLine> listOps;

	public Boolean save(String user, ItemLine itemLine) {
		
		String jsonValue = template.opsForValue().get(user);
		try {
			List<ItemLine> productList;
			
			if(jsonValue != null) {
				productList = objectMapper.readValue(jsonValue,
						new TypeReference<List<ItemLine>>(){});
				
				Optional<ItemLine> existingProduct = productList.stream()
				.filter(product -> itemLine.getItem().getId()==product.getItem().getId())
				.findFirst();
				
				if(existingProduct.isPresent()) {
					productList.remove(existingProduct.get());
					productList.add(itemLine);
				}else {
					productList.add(itemLine);
				}
			} else {
				productList = List.of(itemLine);
			}
			
			template.opsForValue().set(user, objectMapper.writeValueAsString(productList));
			return true;
		}catch (Exception e){
			System.err.println("Error processing JSON: "+e.getMessage());
			return false;
		}
	}

	public List<ItemLine> findAll(String user) {
		String json = template.opsForValue().get(user);
		if(json!=null) {
			try {
				return objectMapper.readValue(json,
						new TypeReference<List<ItemLine>>() {
						});
			}catch (Exception e){
				System.err.println("Error converting JSON to List<ItemLine> : "+user);
			}
		}else {
			System.out.println("Key not founf in Redis : "+user);
		}
		return null;
	}

	public Boolean clear(String user) {
		Boolean delete = template.delete(user);
		return delete;
	}
	
	public Optional<ItemLine> findById(int id, String user) {
		String jsonValue = template.opsForValue().get(user);
		if(jsonValue!=null) {
			try {
				List<ItemLine> jsonList = objectMapper.readValue(jsonValue, 
						new TypeReference<List<ItemLine>>() {
				});
				
				return jsonList.stream().filter(
						jsonObject -> id==(int)jsonObject.getItem().getId())
				.findFirst();
			}catch(Exception e) {
				System.err.println("Error parsing JSON : "+e.getMessage());
			}
		} else {
			System.out.println("Key not found in Redis");
		}
		return Optional.empty();
	}
	
	public double findTotalAmount(String user) {
		String json = template.opsForValue().get(user);
		if(json!=null) {
			try {
				List<ItemLine> value = objectMapper.readValue(json,
						new TypeReference<List<ItemLine>>() {
						});
				double sum = value.stream().mapToDouble(i-> i.getItem().getItemtotal())
				.sum();
				return sum;
			}catch (Exception e){
				System.err.println("Error converting JSON to List<ItemLine> : "+user);
			}
		}else {
			System.out.println("There is no products added for this user : "+user);
		}
		return 0;
	}
	
}