package com.dhanush.cart.exception;
public class CustomFeignClientException extends RuntimeException {
    public CustomFeignClientException(String message) {
        super(message);
    }
}
