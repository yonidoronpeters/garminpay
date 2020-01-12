package com.garmin.interview.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

@EnableOAuth2Client
@Configuration
public class InterviewConfiguration
{
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@ConfigurationProperties(prefix = "security.oauth2.client.fitpay")
	@Bean
	public OAuth2ProtectedResourceDetails fitpayClientClientCreds() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public OAuth2RestTemplate messagingClientClientCredsRestTemplate(
			@Qualifier("fitpayClientClientCreds") OAuth2ProtectedResourceDetails resourceDetails) {
		return new OAuth2RestTemplate(resourceDetails);
	}
}
