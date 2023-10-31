package com.example.demo.Services;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.UserAuthTokenDao;
import com.example.demo.Dao.UserLoginDao;
import com.example.demo.ExceptionPojo.UserAlreadyExists;
import com.example.demo.ExceptionPojo.UserEmailOrPasswordInvalid;
import com.example.demo.PasswordServices.PasswordService;
import com.example.demo.entity.UserLogin;

@Service
public class UserLoginService {
	
	@Autowired
	UserLoginDao userlogindao;
	
	@Autowired
	UserAuthTokenDao userauthtokendao;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	@Autowired
	PasswordService pass;
	
	public String getnewuser(UserLogin user) throws Exception {
		boolean useremail=userlogindao.existsById(user.getEmail());
		if(useremail) {
			throw new UserAlreadyExists("User Alreday Exists.");
		}
		else {
			BCryptPasswordEncoder bcrypt=(BCryptPasswordEncoder) pass.encoder();
			user.setPassword(bcrypt.encode(user.getPassword()));
			userlogindao.save(user);
			return "user saved";
		}
	}
	
	
	public String getAuthecastion(UserLogin user) throws Exception {
		UserLogin useremail=userlogindao.getReferenceById(user.getEmail());
		if(useremail!=null) {
			if(passwordencoder.matches(user.getPassword(),useremail.getPassword())) {
				System.out.println("matched");
				return "matched";
			}
		}
		else {
			return "No user exixtes";
		}
		throw new UserEmailOrPasswordInvalid("Invalid Email or Password");
	}
	
	public String getforgetpassword(UserLogin user) {
		UserLogin useremail=userlogindao.getReferenceById(user.getEmail());
		if(user.isForgetpassword() && useremail!=null) {
			userauthtokendao.getAuthToken("DKF-TOkenGThYd");
			return "done c the db";
		}
		else {
			return "No user found";
		}
	}
}
