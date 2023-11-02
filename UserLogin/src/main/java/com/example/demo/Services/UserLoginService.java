package com.example.demo.Services;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dao.UserAuthTokenDao;
import com.example.demo.Dao.UserLoginDao;
import com.example.demo.ExceptionPojo.InvalidUserEmail;
import com.example.demo.ExceptionPojo.UserAlreadyExists;
import com.example.demo.ExceptionPojo.UserEmailOrPasswordInvalid;
import com.example.demo.PasswordServices.PasswordService;
import com.example.demo.Pojo.UserNewPassword;
import com.example.demo.entity.UserAuthToken;
import com.example.demo.entity.UserLogin;
import com.example.demo.responcePojo.UserAuthTokenResponce;

@Service
public class UserLoginService {
	
	@Autowired
	UserLoginDao userlogindao;
	
	@Autowired
	UserAuthTokenDao userauthtokendao;
	
	@Autowired
	PasswordEncoder passwordencoder;
	
	
	UserAuthToken userauthtoken;
	
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
				return "login successs";
			}
		}
		else {
			return "No user exixtes";
		}
		throw new UserEmailOrPasswordInvalid("Invalid Email or Password");
	}
	
	public UserAuthTokenResponce getforgetpassword(UserLogin user) throws InvalidUserEmail {
		UserLogin useremail=userlogindao.getReferenceById(user.getEmail());
		if(user.isForgetpassword() && useremail!=null) {
//			adding the auto genrate 
//			SecureRandom random=new SecureRandom();
//			byte[] values=new byte[7];
//			random.nextBytes(values);
//			String token=values.toString();
			String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890";
		    String token = "";
		    for (int a = 1; a <= 5; a++) {
		        token += CharSet.charAt(new Random().nextInt(CharSet.length()));
		    }
			
			
			
			userauthtokendao.getAuthToken(token); // this is adding the token 
			
			UserAuthTokenResponce authtoken=new UserAuthTokenResponce();
			authtoken.setAuthToken(token);
			return authtoken;
			
		}
		else {
			throw new InvalidUserEmail("Invalid user email");
		}
	}
	
	public String setnewpassword(String authToken,UserNewPassword usernewpassword) {
		boolean usertoken=userauthtokendao.existsById(authToken);
		UserLogin useremail=userlogindao.getReferenceById(usernewpassword.getEmail());
		if(usertoken && useremail!=null &&((usernewpassword.getConfirmPassword()).equals(usernewpassword.getNewPassword()))) {
			UserLogin user=userlogindao.getReferenceById(usernewpassword.getEmail());
			BCryptPasswordEncoder bcrypt=(BCryptPasswordEncoder) pass.encoder();
			user.setPassword(bcrypt.encode(usernewpassword.getNewPassword()));
			userlogindao.save(user);
			userauthtokendao.deleteAll();
			return "updated";
		}
		else {
			return "not updated";
		}
		
	}
}
