package com.leo.chatSpec.controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import com.leo.chatSpec.models.User;

@Component
public class PrincipalExtractor extends DefaultHandshakeHandler{
	
	
	@Override
	protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler,
            Map<String, Object> attributes) {
//		return (Principal) attributes.get("principal");
		User user = (User) attributes.get("user");
        if (user == null) {
            return null;
        }
        return () -> user.getSeshId(); // This becomes the user's STOMP identity
	}
}
