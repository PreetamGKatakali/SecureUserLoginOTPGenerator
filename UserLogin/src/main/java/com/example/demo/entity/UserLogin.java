package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserLogin {
	
	@Id
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private boolean forgetpassword;

	public UserLogin() {     //default 
	
	}
	
	public UserLogin(String email, String password, boolean forgetpassword) {
		super();
		this.email = email;
		this.password = password;
		this.forgetpassword = forgetpassword;
	}
	
	
	//getters and setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isForgetpassword() {
		return forgetpassword;
	}

	public void setForgetpassword(boolean forgetpassword) {
		this.forgetpassword = forgetpassword;
	}
	
	
	//tostring

	@Override
	public String toString() {
		return "UserLogin [email=" + email + ", password=" + password + ", forgetpassword=" + forgetpassword + "]";
	}

	
	
	
	
	
}
