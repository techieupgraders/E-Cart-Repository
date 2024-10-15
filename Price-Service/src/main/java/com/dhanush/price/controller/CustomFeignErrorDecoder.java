package com.dhanush.price.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.dhanush.price.dto.ErrorResponseDto;
import com.dhanush.price.exception.CustomFeignClientException;
import com.dhanush.price.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecoder implements ErrorDecoder {

	private final ErrorDecoder defaultDecoder = new Default();

	@Override
	public Exception decode(String methodKey, Response response) {
		if (response.status() == 404) {
			String message = null;
			// Get the message from the response body
			String responseBody;
			try {
				responseBody = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
				ObjectMapper mapper = new ObjectMapper();
				JsonNode errorNode = mapper.readTree(responseBody);
				message = errorNode.path("errorMessage").asText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Now parse the message string to extract resourceName, fieldName, and
			// fieldValue
			return new ResourceNotFoundException(message);

		}
		if (response.status() >= 500) {
			// Retrieve the error message from the response body, if available
			String message = "Internal Server Error occurred in downstream service";
			try {
				message = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new CustomFeignClientException(message);
		}
		return defaultDecoder.decode(methodKey, response);
	}
}
