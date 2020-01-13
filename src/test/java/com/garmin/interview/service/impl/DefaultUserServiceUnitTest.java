package com.garmin.interview.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import com.garmin.interview.dto.FitPayUser;
import com.garmin.interview.exception.UserDoesNotExist;

@ExtendWith(MockitoExtension.class)
class DefaultUserServiceUnitTest
{
	@Mock
	private OAuth2RestTemplate restTemplate;
	@InjectMocks
	private DefaultUserService userService;

	@BeforeEach
	public void setUp() {
		ReflectionTestUtils.setField(userService, "url", "http://test");
	}

	@Test
	void test400IsReturnedFromServer()
	{
		when(restTemplate.getForEntity(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

		assertThrows(UserDoesNotExist.class, () -> userService.getUserById("123"));
	}

	@Test
	void testExceptionIsPropagatedWhenDifferentErrorCodeReturnedFromServer()
	{
		when(restTemplate.getForEntity(anyString(), any())).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

		assertThrows(HttpClientErrorException.class, () -> userService.getUserById("123"));
	}

	@Test
	void testUserFromResponseBodyIsReturned()
	{
		final ResponseEntity responseEntity = mock(ResponseEntity.class);
		final FitPayUser user = mock(FitPayUser.class);
		when(responseEntity.getBody()).thenReturn(user);
		when(restTemplate.getForEntity(anyString(), any())).thenReturn(responseEntity);

		final FitPayUser returnedUser = userService.getUserById("123");

		assertEquals(user, returnedUser);
	}
}