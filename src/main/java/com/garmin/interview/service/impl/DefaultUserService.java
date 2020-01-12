package com.garmin.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.exception.UserDoesNotExist;
import com.garmin.interview.service.UserService;

@Service
public class DefaultUserService implements UserService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);
	@Value("${fitpay.users.url}")
	private String url;
	private final OAuth2RestTemplate restTemplate;

	public DefaultUserService(final OAuth2RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public FitPayUser getUserById(final String userId)
	{
		final String requestUrl = String.format(this.url, userId);
		LOG.info("URL for request: {}", requestUrl);

		try
		{
			final ResponseEntity<FitPayUser> response = restTemplate.getForEntity(requestUrl, FitPayUser.class);
			return response.getBody();
		}
		catch (final HttpClientErrorException e)
		{
			LOG.error("Encountered an error while processing the request", e);
			if (e.getStatusCode().equals(HttpStatus.BAD_REQUEST))
			{
				throw new UserDoesNotExist(userId);
			}
			throw e;
		}
	}
}
