package com.dhanush.price.exception;
public class CustomFeignClientException extends RuntimeException {
    public CustomFeignClientException(String message) {
        super(message);
    }
}
