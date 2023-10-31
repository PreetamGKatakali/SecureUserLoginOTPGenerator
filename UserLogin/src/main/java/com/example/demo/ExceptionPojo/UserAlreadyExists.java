package com.example.demo.ExceptionPojo;

public class UserAlreadyExists extends Exception{
	
	private String message;

	public UserAlreadyExists(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
