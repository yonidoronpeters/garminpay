package com.garmin.interview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.garmin.interview.exception.UserDoesNotExist;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler
{
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserDoesNotExist.class)
	protected UserDoesNotExist handleUserNotFound(final UserDoesNotExist e) {
		return e;
	}
}
