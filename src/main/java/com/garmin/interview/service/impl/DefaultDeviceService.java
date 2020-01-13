package com.garmin.interview.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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

		final CollectionEntity collectionEntity = response.getBody();

		final Collection<Map<String, Object>> devices = collectionEntity.getResults();
		LOG.debug("Devices for user: {}", devices);

		final Collection<Map<String, Object>> allDevices = new ArrayList<>(devices);
		getAllPages(allDevices, collectionEntity);

		return allDevices;
	}

	private void getAllPages(final Collection<Map<String, Object>> allDevices, final CollectionEntity collectionEntity)
	{
		CollectionEntity current = collectionEntity;
		while (current.get_links().getNext() != null)
		{
			final String nextPageUrl = current.get_links().getNext().getHref();
			LOG.info("Getting next page of devices from: {}", nextPageUrl);
			final ResponseEntity<CollectionEntity> nextPage = restTemplate.getForEntity(
					nextPageUrl, CollectionEntity.class);
			allDevices.addAll(nextPage.getBody().getResults());
			current = nextPage.getBody();
		}
	}
}
