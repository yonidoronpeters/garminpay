package com.garmin.interview.dto;

import java.util.Collection;

import lombok.Data;

@Data
public class CollectionEntity<T>
{
	private String limit;
	private String offset;
	private String totalResults;
	private NavigationLinks _links;
	private Collection<T> results;
}
