package com.example.springboot;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class HelloController {

	@Autowired
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;
	
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
	@RequestMapping(value = "/data", method = RequestMethod.GET)
	public String getData() {
		System.out.println("Returning data from nt-gateway own data method");
		return "Hello from NT-GATEWAY-data method";
	}
	
	@RequestMapping(value = "/ms-data", method = RequestMethod.GET)
	public String getMsData() {
		System.out.println("Got inside NT-GATEWAY-ms-data method");
		try {
			String msEndpoint = env.getProperty("endpoint.ms-service");
			System.out.println("MS Endpoint name : [" + msEndpoint + "]");
			
			return restTemplate.getForObject(new URI(msEndpoint), String.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Exception occurred.. so, returning default data";
	}
	
}
