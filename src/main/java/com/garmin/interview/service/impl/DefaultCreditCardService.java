package com.garmin.interview.service.impl;

import java.net.URI;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.garmin.interview.dto.CollectionEntity;
import com.garmin.interview.dto.CreditCard;
import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.service.CreditCardService;
import com.garmin.interview.util.HttpHeader;

@Service
public class DefaultCreditCardService implements CreditCardService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultCreditCardService.class);
	@Value("${fitpay.oauth.token}")
	private String token;
	private final RestTemplate restTemplate;

	public DefaultCreditCardService(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public Collection<CreditCard> getCreditCardsForUser(final FitPayUser user)
	{
		final String requestUrl = user.get_links().getCreditCards().getHref();
		LOG.info("Credit cards url for user: {}", requestUrl);

		final ResponseEntity<CollectionEntity> response = restTemplate.exchange(
				URI.create(requestUrl),
				HttpMethod.GET,
				new HttpEntity<>(HttpHeader.forToken(token)),
				CollectionEntity.class);

		final Collection<CreditCard> devices = response.getBody().getResults();
		LOG.info("Credit cards for user: {}", devices);
		return devices;
	}
}
