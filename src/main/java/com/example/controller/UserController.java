package com.example.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.IUserService;

@RestController
@RequestMapping(value = "user-management")
public class UserController {

	Logger log = LogManager.getLogger(UserController.class);

	@Autowired
	IUserService userService;

	@PostMapping("/managed-user")
	public User saveEmployee(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PutMapping("/managed-user")
	public String updateEmployee(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@GetMapping("/managed-user/{id}")
	public User getEmployee(@PathVariable Long id) {
		return userService.getUserById(id);
	}

	@DeleteMapping("/managed-user/{id}")
	public String removeEmployee(@PathVariable(value = "id") Long id) {
		return userService.removeUser(id);
	}

	@GetMapping("/managed-user")
	public List<User> getAllEmployee() {
		return userService.getAllUsers();
	}

}
