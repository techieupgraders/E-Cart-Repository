package com.dhanush.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class SecurityFilterConfig {

//	@Bean
//	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
//		httpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
//				.pathMatchers("/e-cart/product-catalog-service/**").authenticated()
//				.pathMatchers("/e-cart/price-service/**").authenticated()
//				.pathMatchers("/e-cart/cart-service/**").authenticated()
//				.pathMatchers("/e-cart/order-service/**").authenticated()
//				).oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
//		httpSecurity.csrf(csrf -> csrf.disable());
//		return httpSecurity.build();
//	}
	
	@Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.authorizeExchange(exchanges -> exchanges.pathMatchers(HttpMethod.GET).permitAll()
                .pathMatchers("/e-cart/product-catalog-service/**").hasRole("USER")
                .pathMatchers("/e-cart/price-service/**").hasRole("ADMIN")
                .pathMatchers("/e-cart/cart-service/**").hasRole("USER")
        		.pathMatchers("/e-cart/order-service/**").hasRole("USER"))
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
        serverHttpSecurity.csrf(csrfSpec -> csrfSpec.disable());
        return serverHttpSecurity.build();
    }
	
	private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter =
                new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter
                (new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
	
}
