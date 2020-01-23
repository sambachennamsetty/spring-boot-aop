package com.example.service;

import java.util.List;

import com.example.entity.User;
public interface IUserService {

	public User saveUser(User user);
	public String updateUser(User user);
	public User getUserById(Long id);
	public String removeUser(Long id);
	public List<User> getAllUsers();
	
	
}
