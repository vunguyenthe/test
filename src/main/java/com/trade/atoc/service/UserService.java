package com.trade.atoc.service;

import java.util.List;

import com.trade.atoc.beans.User;

public interface UserService {

	User findById(String id);

	User findByName(String name);

	void updateUser(User user);

	void deleteUserById(String id);

	List<User> findAllUsers();

	void deleteAllUsers();

	public boolean isUserExist(User user);

}
