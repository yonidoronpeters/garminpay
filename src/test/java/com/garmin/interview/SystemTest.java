package com.garmin.interview;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.garmin.interview.controller.CompositeController;
import com.garmin.interview.dto.CompositeUser;

@SpringBootTest
class SystemTest
{
	// ideally a user would be created as a setup for this test instead of relying on an existing userId
	private static final String USER_ID = "b1ad2850-6ba5-4922-8332-9ec5eae2fc67";
	@Autowired
	private CompositeController controller;

	@Test
	void testEndToEnd()
	{
		final ResponseEntity<CompositeUser> response = controller.getCustomerById(USER_ID, null, null);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		final CompositeUser compositeUser = response.getBody();
		assertNotNull(compositeUser);
		assertEquals(2, compositeUser.getCreditCards().size());
		assertEquals(2, compositeUser.getDevices().size());
	}
}