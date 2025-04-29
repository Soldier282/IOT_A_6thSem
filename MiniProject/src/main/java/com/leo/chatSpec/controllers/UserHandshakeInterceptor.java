package com.leo.chatSpec.controllers;

import java.time.LocalDateTime;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.leo.chatSpec.models.Connection;
import com.leo.chatSpec.models.User;


@Configuration
public class UserHandshakeInterceptor implements HandshakeInterceptor {
	
	@Bean
	UserHandshakeInterceptor getBean() {
		return new UserHandshakeInterceptor();
	}
	
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		String query = request.getURI().getQuery();
		String server = null;
		if (query != null) {
	        for (String param : query.split("&")) {
	            String[] pair = param.split("=");
	            if (pair.length == 2) {
	                if ("server".equals(pair[0])) {
	                    server = pair[1];
	                }
	            }
	        }
	    }
		System.out.println("Executed");
		User user = new User(Connection.generateBase62Id(), server);
		attributes.put("user", user);
//		Principal principal = () -> user.getSeshId(); 
//	    if (request instanceof ServletServerHttpRequest servletRequest) {
//	        servletRequest.getServletRequest().setAttribute("principal", principal);
//	    }
		return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		System.out.println("Handshake completed for: "+ request.getRemoteAddress()+" at: "+ LocalDateTime.now());
	}
	
}
