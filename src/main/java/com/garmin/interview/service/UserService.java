package com.garmin.interview.service;

import com.garmin.interview.dto.CompositeUser;

public interface UserService
{
	CompositeUser getCompositeInfo(String userId);
}
