package com.garmin.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garmin.interview.dto.CompositeUser;
import com.garmin.interview.service.UserService;

@RestController
@RequestMapping("/compositeUsers")
public class CompositeController
{
	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public CompositeUser getCustomerById(@PathVariable String userId) {
		return userService.getCompositeInfo(userId);
	}
}
