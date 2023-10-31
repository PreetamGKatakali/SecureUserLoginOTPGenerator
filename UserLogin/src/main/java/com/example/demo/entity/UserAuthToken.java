package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserAuthToken {
	
	@Id
	private String authToken;

	public UserAuthToken() {
		super();
	}

	public UserAuthToken(String authToken) {
		super();
		this.authToken = authToken;
	}
	
	

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	@Override
	public String toString() {
		return "UserAuthToken [authToken=" + authToken + "]";
	}
	
	
}
