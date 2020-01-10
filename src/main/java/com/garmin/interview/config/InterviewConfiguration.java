/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2013 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package com.garmin.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.client.RestTemplate;

@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
@Configuration
public class InterviewConfiguration
{
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
