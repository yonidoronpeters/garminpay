package com.garmin.interview.dto;

import java.util.Collection;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@With
@NoArgsConstructor
@AllArgsConstructor
public class CompositeUser
{
	private String userId;
	private Collection<Map<String, Object>> creditCards;
	private Collection<Map<String, Object>> devices;
}
