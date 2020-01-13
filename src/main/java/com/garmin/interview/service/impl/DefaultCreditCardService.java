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
import com.garmin.interview.service.CreditCardService;

@Service
public class DefaultCreditCardService implements CreditCardService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultCreditCardService.class);
	private final OAuth2RestTemplate restTemplate;

	public DefaultCreditCardService(final OAuth2RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}

	@Override
	public Collection<Map<String, Object>> getCreditCardsForUser(final FitPayUser user)
	{
		final String requestUrl = user.get_links().getCreditCards().getHref();
		LOG.info("Credit cards url for user: {}", requestUrl);

		final var response = restTemplate.getForEntity(requestUrl, CollectionEntity.class);

		final CollectionEntity collectionEntity = response.getBody();
		final Collection<Map<String, Object>> creditCards = collectionEntity.getResults();
		LOG.debug("Credit cards for user: {}", creditCards);

		final Collection<Map<String, Object>> allCreditCards = new ArrayList<>(creditCards);
		getAllPages(allCreditCards, collectionEntity);

		return allCreditCards;
	}

	private void getAllPages(final Collection<Map<String, Object>> allCreditCards, final CollectionEntity collectionEntity)
	{
		CollectionEntity curr = collectionEntity;
		while (curr.get_links().getNext() != null)
		{
			final String nextPageUrl = curr.get_links().getNext().getHref();
			LOG.info("Getting next page of credit cards from: {}", nextPageUrl);
			final ResponseEntity<CollectionEntity> nextPage = restTemplate.getForEntity(
					nextPageUrl, CollectionEntity.class);
			allCreditCards.addAll(nextPage.getBody().getResults());
			curr = nextPage.getBody();
		}
	}
}
