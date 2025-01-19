package com.dhanush.prod.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dhanush.prod.exception.AuthInterceptorException;
import com.dhanush.prod.exception.GlobalExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

	private static final String USERNAME = "admin";
	private static final String PASSWORD = "admin";

	// curl --location
	// 'http://localhost:8080/e-cart/product-catalog-service/api/products/byName/l'
	// \
	// --header 'Authorization: Basic YWRtaW46YWRtaW4='

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("AuthInterceptor preHandle executed");
		String header = request.getHeader("Authorization");
		if (StringUtils.hasText(header) && header.startsWith("Basic ")) {
			String base64 = header.substring("Basic ".length());
			byte[] decode = Base64.getDecoder().decode(base64);

			String usernameAndPassword = new String(decode, StandardCharsets.UTF_8); // admin:admin

			String[] split = usernameAndPassword.split(":");

			if (USERNAME.equals(split[0]) && PASSWORD.equals(split[1])) {
				return true;
			}
		}
			throw new AuthInterceptorException("unauthorized");
		}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("AuthInterceptor postHandle executed");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("AuthInterceptor afterCompletion executed");
	}

}
