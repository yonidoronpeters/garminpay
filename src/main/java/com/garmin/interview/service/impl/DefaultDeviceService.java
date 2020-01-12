package com.garmin.interview.service.impl;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.garmin.interview.dto.CollectionEntity;
import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.service.DeviceService;

@Service
public class DefaultDeviceService implements DeviceService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultDeviceService.class);
	private final OAuth2RestTemplate restTemplate;

	public DefaultDeviceService(final OAuth2RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public Collection<Map<String, Object>> getDevicesForUser(final FitPayUser user)
	{
		final String requestUrl = user.get_links().getDevices().getHref();
		LOG.info("Devices url for user: {}", requestUrl);

		final var response = restTemplate.getForEntity(requestUrl, CollectionEntity.class);

		final Collection<Map<String, Object>> devices = response.getBody().getResults();
		LOG.debug("Devices for user: {}", devices);
		return devices;
	}
}
