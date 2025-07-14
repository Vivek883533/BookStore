package com.bookStore.bookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookStore.bookStore.Entity.User;
import com.bookStore.bookStore.service.UserService;

@RestController
public class UserRegistration {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/registerUser")
	public ResponseEntity<String> registerUser(@RequestBody User user) {

		try {
			String result = userService.addUser(user);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@PostMapping("/authenticateUser")
	public ResponseEntity<String> authenticateUser(@RequestBody User user) {

		try {
			String result = userService.authenticateUser(user);

			if (result == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
