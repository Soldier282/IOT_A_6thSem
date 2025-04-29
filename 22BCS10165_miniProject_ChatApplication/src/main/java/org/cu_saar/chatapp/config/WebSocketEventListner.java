package org.cu_saar.chatapp.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.cu_saar.chatapp.MessageeType;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.cu_saar.chatapp.ChatMessage;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListner {

    private final SimpMessageSendingOperations messageTemplate;

    public void handleWebSocketDisconnectListner(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null){
            log.info("User Disconnected: {}",username);
            var chatmessage = ChatMessage.builder().type(MessageeType.LEAVE).sender(username).build();

            messageTemplate.convertAndSend("/topic/public",chatmessage);

        }
    }

}
