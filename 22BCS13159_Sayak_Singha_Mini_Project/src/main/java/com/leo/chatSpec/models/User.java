package com.leo.chatSpec.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class User {
	@NotNull
	@NotEmpty
	private String seshId;
	private String server;
	
	public User(String seshId, String server)
	{
		this.seshId = seshId;
		this.server = server;
	}
	public String getSeshId(){
		return seshId;
	}
	
	public void setSeshId(String seshId) {
		this.seshId = seshId;
	}
	
	public String getServer() {
		return this.server;
	}
	
	public void setServer(String server) {
		this.server = server;
	} 
}
