package com.shop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.entity.Category;

/**
 * Class utils for creating and initializing category object
 */
public class CategoryUtils {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static String getCategoryJson() throws JsonProcessingException {

		return mapper.writeValueAsString(getCategory());
	}

	public static Category getCategory(){

		Category category = new Category();

		category.setName("computers");
		category.setDescription("Computer description");

		return category;
	}
}
