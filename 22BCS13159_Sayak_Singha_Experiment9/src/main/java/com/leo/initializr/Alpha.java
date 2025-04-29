package com.leo.initializr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Alpha {
	
	@GetMapping("")
	public String out()
	{
		return "go to \\api\\appInf";
	}
}
