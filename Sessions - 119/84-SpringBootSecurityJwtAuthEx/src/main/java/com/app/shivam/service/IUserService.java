package com.app.shivam.service;

import com.app.shivam.entity.User;

public interface IUserService {

	public Integer saveUser(User user);
	public User findByUsername(String username);
}
