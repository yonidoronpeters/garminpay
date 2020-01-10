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
