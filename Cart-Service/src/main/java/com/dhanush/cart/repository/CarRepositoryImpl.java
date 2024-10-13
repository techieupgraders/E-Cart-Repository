package com.dhanush.cart.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.dhanush.cart.model.ItemLine;

import jakarta.annotation.Resource;

@Repository
public class CarRepositoryImpl {

	@Autowired
	private RedisTemplate<String, String> template;

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, ItemLine> listOps;

	public ItemLine save(String user, ItemLine itemLine) {
		listOps.rightPush(user, itemLine); // ["a","b","c",...]
		return itemLine;
	}

	public List<ItemLine> findAll(String user) {
		return listOps.range(user, 0, -1);
	}

	public void clear(String user) {
		template.delete(user);
	}
}