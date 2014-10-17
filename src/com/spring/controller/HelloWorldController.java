package com.spring.controller;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;  
  
@Controller
@RequestMapping("/hello")
public class HelloWorldController {  
  
	@RequestMapping(method=RequestMethod.GET)  
	public ModelAndView helloWorld() {  
		String message = "Welcome to Spring 4.0 !";  
		return new ModelAndView("hello", "message", message);  
	}
}