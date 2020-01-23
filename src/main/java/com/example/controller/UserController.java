package com.example.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		String response = userService.updateUser(user);
		return response;
	}

	@GetMapping("/managed-user-get")
	public User getEmployee(@RequestParam Long id) {
		User response = userService.getUserById(id);
		return response;
	}

	@DeleteMapping("/managed-user")
	public String removeEmployee(@RequestParam Long id) {
		String response = userService.removeUser(id);
		return response;
	}

	@GetMapping("/managed-user")
	public List<User> getAllEmployee() {
		List<User> response = userService.getAllUsers();
		System.out.println("inside method");
		return response;
	}

}
