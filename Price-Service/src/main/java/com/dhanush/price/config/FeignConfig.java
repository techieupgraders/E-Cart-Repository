package com.dhanush.price.config;

import org.springframework.context.annotation.Bean;

import com.dhanush.price.controller.CustomFeignErrorDecoder;

import feign.codec.ErrorDecoder;

public class FeignConfig {

	@Bean
	public ErrorDecoder errorDecoder() {
		return new CustomFeignErrorDecoder();
	}

}