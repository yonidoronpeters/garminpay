package com.garmin.interview.service;

import java.util.Collection;
import java.util.Map;

import com.garmin.interview.dto.FitPayUser;

public interface DeviceService
{
	Collection<Map<String, Object>> getDevicesForUser(FitPayUser user);
}
