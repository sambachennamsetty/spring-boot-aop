package com.example.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repo.UserRepository;
import com.example.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public String updateUser(User user) {
		return userRepository.findById(user.getId()).map(u -> {
			u.setAge(user.getAge());
			u.setName(user.getName());
			userRepository.save(u);
			return "User Updated Successfully";
		}).orElseThrow(() -> new NullPointerException("Record not found exception for id:" + user.getId()));
		// for sample i am using nullPointerException , basically we have to use
		// customException class
	}

	@Override
	public User getUserById(Long id) {
		Optional<User> gotUser = userRepository.findById(id);
		if (gotUser.isPresent()) {
			return gotUser.get();
		}
		return (User) new Object();
	}

	@Override
	public String removeUser(Long id) {
		return userRepository.findById(id).map(u -> {
			userRepository.deleteById(id);
			return "User '" + id + "' deleted Successfully";
		}).orElseThrow(() -> new NullPointerException("No record found for id" + id));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
