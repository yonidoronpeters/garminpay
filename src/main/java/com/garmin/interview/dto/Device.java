package com.garmin.interview.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class Device
{
	@JsonAlias("deviceIdentifier")
	private String deviceId;
	private String state;
}
