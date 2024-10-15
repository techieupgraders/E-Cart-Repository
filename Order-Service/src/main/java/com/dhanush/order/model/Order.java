package com.dhanush.order.model;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDateTime date;
	private double amount;
	private String user;

	public static Order jsonStringToJavaObject(String json) throws IOException {

		Order confirmation = new Order();

		// InputStream is = new ByteArrayInputStream(json.getBytes());
		// InputStream is = new ByteArrayInputStream(json.getBytes());
		// JsonReader jsonReader = Json.createReader(is);
		StringReader sr = new StringReader(json);
		JsonReader jsonReader = Json.createReader(sr);
		JsonObject jsonObject = jsonReader.readObject();

		confirmation.setId(jsonObject.getInt("id"));
		String date = jsonObject.getString("date");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(date, dtf);
		confirmation.setDate(dateTime);
		confirmation.setAmount(jsonObject.getInt("amount"));
		confirmation.setUser(jsonObject.getString("user"));

		return confirmation;
	}
}
