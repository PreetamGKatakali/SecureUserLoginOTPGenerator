package com.example.demo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserLogin;

public interface UserLoginDao extends JpaRepository<UserLogin,String>{
	
	@Query("select us from UserLogin us where us.email=:n")
	public UserLogin getuserbyemail(@Param("n")String email);
}
