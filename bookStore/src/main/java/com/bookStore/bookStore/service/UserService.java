package com.bookStore.bookStore.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.bookStore.Entity.User;
import com.bookStore.bookStore.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public String addUser(User user) {
		logger.info("In addUser method of userRepo");
		String result = null;
		try {
			userRepo.save(user);
			result = "success";
		} catch (Exception e) {
			result = "Error";
			logger.error("Error Occurred " + e);
		}
		return result;
	}

	public String authenticateUser(User user) {
		logger.info("In authenticateUser method of userRepo");
		String result = null;
		try {
			User existingUser = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
			if (existingUser != null) {
				result = "Login Successfull";
			} else {
				result = "login failed";
			}

		} catch (Exception e) {
			logger.error("Error Occurred " + e);
		}
		return result;

	}

}
