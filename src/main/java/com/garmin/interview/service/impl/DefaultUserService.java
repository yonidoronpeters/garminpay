package com.garmin.interview.service.impl;

import org.springframework.stereotype.Component;

import com.garmin.interview.dto.CompositeUser;
import com.garmin.interview.service.UserService;

@Component
public class DefaultUserService implements UserService
{
	@Override
	public CompositeUser getCompositeInfo(final String userId)
	{
		return null;
	}
}
