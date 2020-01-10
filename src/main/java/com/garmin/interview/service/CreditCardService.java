package com.garmin.interview.service;

import java.util.Collection;

import com.garmin.interview.dto.CreditCard;
import com.garmin.interview.dto.FitPayUser;

public interface CreditCardService
{
	Collection<CreditCard> getCreditCardsForUser(FitPayUser user);
}
