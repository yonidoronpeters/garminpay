package com.garmin.interview.exception;

public class UserDoesNotExist extends RuntimeException
{
	private static final String MSG = "User with id [%s] was not found in the system";
	public UserDoesNotExist(final String userId)
	{
		super(String.format(MSG, userId));
	}
}
