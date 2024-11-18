package com.dhanush.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import reactor.core.publisher.Mono;

@Configuration
public class ResponseTraceFilter {

	private static final Logger logger = LoggerFactory.getLogger(ResponseTraceFilter.class);
	
	@Autowired
	private FilterUtility filterUtility;

	@Bean
	public GlobalFilter postGlobalFilter() {
		return (exchange, chain)-> {
			return chain.filter(exchange).then(Mono.fromRunnable(()-> {
				HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                String trackingId = filterUtility.getTrackingID(requestHeaders);
                logger.debug("Updated the tracking id to the outbound headers: {}", trackingId);
                exchange.getResponse().getHeaders().add(FilterUtility.TRACKING_ID, trackingId);
			}));
		};
	}
}