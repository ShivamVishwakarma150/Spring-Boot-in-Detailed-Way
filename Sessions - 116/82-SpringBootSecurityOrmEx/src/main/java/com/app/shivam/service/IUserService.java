package com.app.shivam.service;

import java.util.Optional;

import com.app.shivam.entity.User;

public interface IUserService {

	Integer saveUser(User user);
	Optional<User> getOneUser(Integer id);
}
