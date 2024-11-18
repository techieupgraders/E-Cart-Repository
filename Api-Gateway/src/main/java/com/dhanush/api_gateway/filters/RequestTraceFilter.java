package com.dhanush.api_gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter{
	
	 private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);
	
	@Autowired
	private FilterUtility filterUtility;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		HttpHeaders headers = exchange.getRequest().getHeaders();
		if(isTrackingIdAvailable(headers)) {
			logger.debug("e-cart Tracking ID found in RequestTraceFilter : {}",
                    filterUtility.getTrackingID(headers));
        } else {
            String trackingID = generateTrackingID();
            exchange = filterUtility.setTrackingID(exchange, trackingID);
            logger.debug("e-cart Tracking ID generated in RequestTraceFilter : {}", trackingID);
        }
        return chain.filter(exchange);
	}

	private String generateTrackingID() {
		return java.util.UUID.randomUUID().toString();
	}

	private boolean isTrackingIdAvailable(HttpHeaders headers) {
		if(filterUtility.getTrackingID(headers)!=null) {
			return true;
		}else {
			return false;
		}
	}

}
