package com.garmin.interview.service;

import java.util.Collection;

import com.garmin.interview.dto.Device;
import com.garmin.interview.dto.FitPayUser;

public interface DeviceService
{
	Collection<Device> getDevicesForUser(FitPayUser user);
}
