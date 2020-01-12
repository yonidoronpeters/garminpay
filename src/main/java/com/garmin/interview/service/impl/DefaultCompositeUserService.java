package com.garmin.interview.service.impl;

import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.garmin.interview.dto.CompositeUser;
import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.service.CompositeUserService;
import com.garmin.interview.service.CreditCardService;
import com.garmin.interview.service.DeviceService;
import com.garmin.interview.service.UserService;

@Service
public class DefaultCompositeUserService implements CompositeUserService
{
	private static final Logger LOG = LoggerFactory.getLogger(DefaultCompositeUserService.class);
	private final UserService userService;
	private final DeviceService deviceService;
	private final CreditCardService creditCardService;

	public DefaultCompositeUserService(final UserService userService,
	                                   final DeviceService deviceService,
	                                   final CreditCardService creditCardService)
	{
		this.userService = userService;
		this.deviceService = deviceService;
		this.creditCardService = creditCardService;
	}

	@Override
	public CompositeUser getCompositeInfo(final String userId)
	{
		final FitPayUser user = userService.getUserById(userId);
		final Collection<Map<String, Object>> devices = deviceService.getDevicesForUser(user);
		final Collection<Map<String, Object>> creditCards = creditCardService.getCreditCardsForUser(user);

		final CompositeUser compositeUser = new CompositeUser()
				.withUserId(user.getUserId())
				.withDevices(devices)
				.withCreditCards(creditCards);

		LOG.debug("Created composite user: {}", compositeUser);

		return compositeUser;
	}
}
