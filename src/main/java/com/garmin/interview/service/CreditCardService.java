package com.garmin.interview.service;

import java.util.Collection;
import java.util.Map;

import com.garmin.interview.dto.FitPayUser;

public interface CreditCardService
{
	Collection<Map<String, Object>> getCreditCardsForUser(FitPayUser user);
}
