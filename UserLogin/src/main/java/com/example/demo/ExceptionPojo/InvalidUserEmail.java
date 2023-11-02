package com.example.demo.ExceptionPojo;

public class InvalidUserEmail extends Exception{
	
	private String message;

	public InvalidUserEmail(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
