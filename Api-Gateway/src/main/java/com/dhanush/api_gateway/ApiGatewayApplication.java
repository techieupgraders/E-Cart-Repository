package com.dhanush.api_gateway;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	//Custom Routing to add /e-cart before each microservice name
	@Bean
	public RouteLocator customRoute(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder
				.routes()
				.route(p -> p.path("/e-cart/product-catalog-service/**")
						.filters(f-> f.rewritePath("/e-cart/product-catalog-service/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
								.circuitBreaker(config -> config.setName("ProductCatalogServiceCB")
								.setFallbackUri("forward:/contactSupport")))
						.uri("lb://PRODUCT-CATALOG-SERVICE"))
				.route(p -> p.path("/e-cart/price-service/**")
						.filters(f-> f.rewritePath("/e-cart/price-service/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://PRICE-SERVICE"))
				.route(p -> p.path("/e-cart/cart-service/**")
						.filters(f-> f.rewritePath("/e-cart/cart-service/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CART-SERVICE"))
				.route(p -> p.path("/e-cart/order-service/**")
						.filters(f-> f.rewritePath("/e-cart/order-service/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ORDER-SERVICE")).build();
	}

}
