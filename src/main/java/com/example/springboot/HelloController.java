package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class HelloController {

	@Autowired
	private Environment env;
	
	@Value("${spring.application.name}")
	private String application;
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot! Docker  " + application ;
		
	}
	@GetMapping("/env")
	public String env() {
		return "Environemnt from property files " + env.getProperty("environment") ;
		//return "Environemnt from property files " + testenv ;
		
	}
	
}
