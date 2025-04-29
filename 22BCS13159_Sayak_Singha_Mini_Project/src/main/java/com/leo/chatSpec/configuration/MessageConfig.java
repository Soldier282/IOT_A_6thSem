package com.leo.chatSpec.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.leo.chatSpec.controllers.PrincipalExtractor;
import com.leo.chatSpec.controllers.UserHandshakeInterceptor;

//import com.leo.chatSpec.models.Message;

@Configuration
@EnableWebSocketMessageBroker
public class MessageConfig implements WebSocketMessageBrokerConfigurer{
	
	@Autowired
	PrincipalExtractor principalExtractor;
	
	@Autowired
	UserHandshakeInterceptor userHandshakeInterceptor;
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setUserDestinationPrefix("/speak");
		registry.setApplicationDestinationPrefixes("/app");
		registry.enableSimpleBroker("/topic", "/queue");
	}
	
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry)
	{
		registry.addEndpoint("/connect").setHandshakeHandler(principalExtractor).addInterceptors(userHandshakeInterceptor).setAllowedOrigins("*").withSockJS();
	}
}
