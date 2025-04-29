package com.leo.chatSpec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import com.leo.chatSpec.models.ChatMessage;
import com.leo.chatSpec.models.User;

@Controller
@Component
public class MessageController{
	
	@Autowired
	public SimpMessagingTemplate template;
	@Autowired
	public Pool connectionPool;
	
	public void sendConnection(String user, String recipientUser) {
		template.convertAndSendToUser(
			    user,
			    "/queue/connect",  
			    recipientUser
			);
	}
	
	public void sendDisconnect(String user) {
		template.convertAndSendToUser(
                user,
                "/queue/disconnect", 
                "Your partner has disconnected."
        );
	}
	
	@MessageMapping("/register")
	public void register(Message<?> stompMessage) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(stompMessage);
		User sender = (User) accessor.getSessionAttributes().get("user");
		String recString = new String();
		if(sender.getServer() == null)
			try {
				recString = connectionPool.connectWithoutServer(sender);
			} catch (InterruptedException e) {
				e.printStackTrace();
		} else {
			try {
				recString = connectionPool.connectWithServer(sender);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(recString != null)
		{
			sendConnection(sender.getSeshId(), recString);
			sendConnection(recString, sender.getSeshId());
		}
		
	}
	
	@MessageMapping("/send")
	public void sendMessage(@Payload ChatMessage message, Message<?> stompMessage) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(stompMessage);
		User sender = (User) accessor.getSessionAttributes().get("user");
		System.out.println(sender.getSeshId());
		String to = message.getRecipient();
		String content = message.getContent();
		System.out.println(content+" "+to);
		if(connectionPool.verify(sender, to))
			template.convertAndSendToUser(to, "/queue/messages", content);
		else
			template.convertAndSendToUser(sender.getSeshId(), "/queue/error", "unauthorized");
		return;
	}
	
	@MessageMapping("/alert")
	public void alert(String info) {
		System.out.println(info);
	}
	
}
