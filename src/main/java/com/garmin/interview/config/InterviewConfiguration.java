package com.garmin.interview.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class InterviewConfiguration
{
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
