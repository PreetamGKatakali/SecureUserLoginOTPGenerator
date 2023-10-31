package com.example.demo.PasswordServices;

import java.security.SecureRandom;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordService {
	
	int strength=10;
	@Bean
	public PasswordEncoder encoder(){
		return new BCryptPasswordEncoder(strength,new SecureRandom());
	}
	
}
