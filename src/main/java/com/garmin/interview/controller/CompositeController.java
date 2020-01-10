package com.garmin.interview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	private static final Logger LOG = LoggerFactory.getLogger(CompositeController.class);
	private final UserService userService;

	public CompositeController(final UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<CompositeUser> getCustomerById(@PathVariable final String userId) {
		LOG.info("Retrieving info for userId: {}", userId);
		final CompositeUser compositeuser = userService.getCompositeInfo(userId);

		return new ResponseEntity<>(compositeuser, HttpStatus.OK);
	}
}
