package com.leo.chatSpec.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ChatMessage{
	@NotNull
	private String content;
	@NotNull
	@NotBlank
	@NotEmpty
	private String recipient;
	
	public ChatMessage(String content, String recipient) {
		this.content = content;
		this.recipient = recipient;
	}
	
	public String getContent()
	{
		return content;
	}
	
	public String getRecipient() {
		return recipient;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
