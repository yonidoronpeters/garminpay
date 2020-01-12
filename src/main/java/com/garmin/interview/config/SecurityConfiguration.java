package com.garmin.interview.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
	@Override
	public void configure(final WebSecurity web)
	{
		web.ignoring()
		   .antMatchers("/webjars/**");
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		    .anyRequest()
		    .permitAll();
	}
}
