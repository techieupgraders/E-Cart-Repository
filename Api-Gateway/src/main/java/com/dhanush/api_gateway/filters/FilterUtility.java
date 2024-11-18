package com.dhanush.api_gateway.filters;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class FilterUtility {
	
	public static final String TRACKING_ID = "Tracking_ID";

	public String getTrackingID(HttpHeaders headers) {
		if(headers.get(TRACKING_ID)!=null) {
			List<String> requestHeaderList = headers.get(TRACKING_ID);
			return requestHeaderList.stream().findFirst().get();
		}else {
			return null;
		}
	}

	public ServerWebExchange setTrackingID(ServerWebExchange exchange, String trackingID) {
		return this.setRequestHeader(exchange,TRACKING_ID ,trackingID);
	}

	private ServerWebExchange setRequestHeader(ServerWebExchange exchange, String trackingId, String trackingID) {
		return exchange.mutate().request(exchange.getRequest().mutate().header(trackingId, trackingID).build()).build();
	}

}
