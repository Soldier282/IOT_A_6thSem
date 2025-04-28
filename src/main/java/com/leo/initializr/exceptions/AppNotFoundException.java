package com.leo.initializr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AppNotFoundException extends RuntimeException{
	public static final long serialVersionUID = 1L;

	public AppNotFoundException()
	{
		super("App Not Found");
	}
}
