package com.leo.chatSpec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.leo.chatSpec.models.User;

@Component
public class DisconnectListener {
	@Autowired
	private Pool connectionPool;
	
	@Autowired
	private MessageController messageController;
	
	@EventListener
	public void handleDisconnect(SessionDisconnectEvent disconnectEvent) {
		System.out.println("Disconnected");
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
		User user = (User) headerAccessor.getSessionAttributes().get("user");
		String rec = null;
		if(user != null) {
			rec = connectionPool.removeUser(user);
			if(rec != null)
			messageController.sendDisconnect(rec);
		}
		System.out.println("User disconnected: "+user.getSeshId());
	}
}
