package com.example.demo.ExceptionPojo;

public class UserEmailOrPasswordInvalid extends Exception {
	
	private String message;

	public UserEmailOrPasswordInvalid(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
