package com.garmin.interview.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garmin.interview.dto.CompositeUser;
import com.garmin.interview.service.CompositeUserService;

@RestController
@RequestMapping("/compositeUsers")
public class CompositeController
{
	private static final Logger LOG = LoggerFactory.getLogger(CompositeController.class);
	private static final String STATE = "state";
	private final CompositeUserService compositeUserService;

	public CompositeController(final CompositeUserService compositeUserService)
	{
		this.compositeUserService = compositeUserService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<CompositeUser> getCustomerById(@PathVariable final String userId,
	                                                     @RequestParam(required = false) final String creditCardState,
	                                                     @RequestParam(required = false) final String deviceState)
	{
		LOG.info("Retrieving info for userId: {}", userId);
		final CompositeUser compositeuser = compositeUserService.getCompositeInfo(userId);
		filterCreditCards(compositeuser, creditCardState);
		filterDevices(compositeuser, deviceState);

		return new ResponseEntity<>(compositeuser, HttpStatus.OK);
	}

	private void filterCreditCards(final CompositeUser compositeuser, final String state)
	{
		if (!Strings.isBlank(state))
		{
			final List<Map<String, Object>> requested = filterCollection(compositeuser.getCreditCards(), state);
			compositeuser.setCreditCards(requested);
		}
	}

	private void filterDevices(final CompositeUser compositeuser, final String state)
	{
		if (!Strings.isBlank(state))
		{
			final List<Map<String, Object>> requested = filterCollection(compositeuser.getDevices(), state);
			compositeuser.setDevices(requested);
		}
	}

	private List<Map<String, Object>> filterCollection(final Collection<Map<String, Object>> collectionToFilter, final String state)
	{
		return collectionToFilter.stream()
		                         .filter(e -> e.get(STATE).equals(state))
		                         .collect(Collectors.toList());
	}
}
