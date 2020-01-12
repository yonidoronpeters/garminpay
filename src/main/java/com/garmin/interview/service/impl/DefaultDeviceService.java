package com.garmin.interview.service.impl;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.garmin.interview.dto.CollectionEntity;
import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.service.DeviceService;
import com.garmin.interview.util.HttpHeader;

@Service
public class DefaultDeviceService implements DeviceService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultDeviceService.class);
	@Value("${fitpay.oauth.token}")
	private String token;
	private final RestTemplate restTemplate;

	public DefaultDeviceService(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public Collection<Map<String, Object>> getDevicesForUser(final FitPayUser user)
	{
		final String requestUrl = user.get_links().getDevices().getHref();
		LOG.info("Devices url for user: {}", requestUrl);

		final ResponseEntity<CollectionEntity> response = restTemplate.exchange(
				URI.create(requestUrl),
				HttpMethod.GET,
				new HttpEntity<>(HttpHeader.forToken(token)),
				CollectionEntity.class);

		final Collection<Map<String, Object>> devices = response.getBody().getResults();
		LOG.info("Devices for user: {}", devices);
		return devices;
	}
}
