package com.leo.chatSpec.models;

import java.security.SecureRandom;
import java.util.Base64;

enum status{
	ALIVE,
	DEAD,
	ERROR
}

public class Connection {
	private User sender;
	private User receiver;
	private status currStatus;
	
	public Connection(User sender, User receiver) {
		this.sender = sender;
		this.receiver = receiver;
		setStatus();
	}
	public void setStatus() {
		if(sender == null || receiver == null)
			currStatus = status.DEAD;
		if(sender != null && receiver != null)
			currStatus = status.ALIVE;
	}
	
	public status getStatus() {
		return currStatus;
	}
	
	public static String generateBase62Id() {
	    byte[] randomBytes = new byte[17];
	    new SecureRandom().nextBytes(randomBytes);
	    return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	}
}
