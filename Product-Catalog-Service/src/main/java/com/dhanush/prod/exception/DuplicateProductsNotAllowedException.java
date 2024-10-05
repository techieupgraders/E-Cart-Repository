package com.dhanush.prod.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DuplicateProductsNotAllowedException extends RuntimeException{

	public DuplicateProductsNotAllowedException(String message) {
		super("Duplicate fields are found : "+message);
	}
}
