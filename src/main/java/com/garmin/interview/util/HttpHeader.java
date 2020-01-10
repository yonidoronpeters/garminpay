package com.garmin.interview.util;

import org.springframework.http.HttpHeaders;

public class HttpHeader
{
	public static HttpHeaders forToken(final String token)
	{
		final HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		return headers;
	}
}
