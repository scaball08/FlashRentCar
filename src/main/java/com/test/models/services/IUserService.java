package com.test.models.services;

import com.test.models.entity.UserLogin;

public interface IUserService {

	public UserLogin findByUsername(String username);
	
	public UserLogin save(UserLogin userLogin);
}
