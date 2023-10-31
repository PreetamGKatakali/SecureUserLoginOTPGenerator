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
import com.example.demo.PasswordServices.PasswordService;
import com.example.demo.entity.UserLogin;

@RestController
public class Mycontroller {
	
	@Autowired
	UserLoginDao userloginpojo;
	
	@Autowired
	PasswordService pass;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public String getuser(@RequestBody UserLogin user) {
		if(user.isForgetpassword()) {
			return "otp will be sent";
		}
		else {
			BCryptPasswordEncoder bcrypt=(BCryptPasswordEncoder) pass.encoder();
			user.setPassword(bcrypt.encode(user.getPassword()));
			userloginpojo.save(user);
			return "user saved";
		}
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public void getpassword(@RequestBody UserLogin userpojo)throws Exception {
		UserLogin user=userloginpojo.getReferenceById(userpojo.getEmail());
		if(user!=null) {
			if(passwordencoder.matches(userpojo.getPassword(),user.getPassword())) {
				System.out.println("matched");
			}
			else {
				System.out.println("not matched");
			}
		}
	
	}
}
