package com.dhanush.prod.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AuthInterceptorException extends RuntimeException{

	public AuthInterceptorException(String message) {
        super(message);
    }
}
