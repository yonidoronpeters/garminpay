package com.garmin.interview.service.impl;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.garmin.interview.dto.CompositeUser;
import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.service.UserService;

@Service
public class DefaultUserService implements UserService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);
	@Value("${fitpay.oauth.token}")
	private String token;
	@Value("${fitpay.users.url}")
	private String url;
	private final RestTemplate restTemplate;

	public DefaultUserService(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public CompositeUser getCompositeInfo(final String userId)
	{
		final String requestUrl = String.format(this.url, userId);
		LOG.info("URL for request: {}", requestUrl);

		final ResponseEntity<FitPayUser> user = restTemplate.exchange(
				URI.create(requestUrl),
				HttpMethod.GET,
				new HttpEntity<>(getHttpHeaders()),
				FitPayUser.class);
		LOG.info("User: {}", user.getBody());

		final CompositeUser compositeUser = new CompositeUser();
		return compositeUser;
	}

	private HttpHeaders getHttpHeaders()
	{
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization","Bearer " + token);
		return headers;
	}
}
