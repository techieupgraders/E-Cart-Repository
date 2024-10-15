package com.dhanush.price.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dhanush.price.controller.CustomFeignErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomFeignErrorDecoder();
    }
}