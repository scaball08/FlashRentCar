package com.test.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.test.models.entity.UserLogin;

public interface UserDao extends JpaRepository<UserLogin, Long>{
	
	public UserLogin findByUsername(String username);

}
