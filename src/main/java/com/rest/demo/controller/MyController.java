package com.rest.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {
	
	@RequestMapping("/")  
	public String hello()   
	{  
	return "Hello all API tests were successful";  
	}  
	}  

