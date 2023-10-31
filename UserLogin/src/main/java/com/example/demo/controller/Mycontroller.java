package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dao.UserLoginDao;
import com.example.demo.ExceptionPojo.UserAlreadyExists;
import com.example.demo.PasswordServices.PasswordService;
import com.example.demo.Services.UserLoginService;
import com.example.demo.entity.UserLogin;

@RestController
public class Mycontroller {
	
	@Autowired
	UserLoginService userloginservice;
	
	@Autowired
	PasswordService pass;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String getnewuser(@RequestBody UserLogin user) throws Exception {
		return userloginservice.getnewuser(user);
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String getpassword(@RequestBody UserLogin user) throws Exception{
		return userloginservice.getAuthecastion(user);
	}
	
	@RequestMapping(value="/forgetpassword",method =  RequestMethod.POST)
	public String getforgetpassword(@RequestBody UserLogin user) {
		System.out.println("hello");
		return userloginservice.getforgetpassword(user);
	}
}
