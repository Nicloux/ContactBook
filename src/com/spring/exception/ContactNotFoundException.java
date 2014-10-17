package com.spring.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Contact Not Found") //404
public class ContactNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ContactNotFoundException() {}
	
	public ContactNotFoundException(String message) {
		super(message);
	}
}
