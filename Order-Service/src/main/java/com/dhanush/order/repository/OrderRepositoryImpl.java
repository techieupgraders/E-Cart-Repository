package com.dhanush.order.repository;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.dhanush.order.model.Order;

import jakarta.annotation.Resource;

@Repository
public class OrderRepositoryImpl {

	@Autowired
	private RedisTemplate<String, String> template;

	// inject the template as ListOperations
	// can also inject as Value, Set, ZSet, and HashOperations
	@Resource(name = "redisTemplate")
	private ListOperations<String, Order> listOps;

	public Optional<Order> getDetailsFromDatabase(String orderId) throws IOException {
        String json = (String) template.opsForValue().get(orderId);
        System.out.println("JSON--------------->"+json);
        if((json==null || json.trim().length() <= 0)){
            return Optional.empty();
        }

        return Optional.of(Order.jsonStringToJavaObject(json));
    }
//	public String retrieve(String user) {
//        //template.opsForHash().entries(user);
//		String data = template.opsForValue().get(user);
//		System.out.println("DAATA : ------------------> "+data);
//		ObjectMapper objmapper = new ObjectMapper();
//		String jsondata="";
//		try {
//			jsondata = objmapper.writeValueAsString(data);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return jsondata;
//	}

	public List<Order> findAll(String user) {
		return listOps.range(user, 0, -1);
	}

	public void clear(String user) {
		template.delete(user);
	}
}