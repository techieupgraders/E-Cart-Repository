package com.dhanush.api_gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

	@RequestMapping("/contactSupport")
	public Mono<String> fallBackResponse(){
		return Mono.just("Some Error happened. Contact Support Team!");
	}
}
