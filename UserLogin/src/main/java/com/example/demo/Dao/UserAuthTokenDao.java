package com.example.demo.Dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.UserAuthToken;

import jakarta.transaction.Transactional;

public interface UserAuthTokenDao extends JpaRepository<UserAuthToken,String> {
	
	@Modifying
	@Query(value ="insert into user_auth_token  (auth_token) VALUES (:n)", nativeQuery = true)
	@Transactional
	public void getAuthToken(@Param("n") String authtoken);
}
