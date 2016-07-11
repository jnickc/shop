package com.shop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.entity.Product;

/**
 * Class utils for creating and initializing product object
 */
public class ProductUtils {

	private static final ObjectMapper mapper = new ObjectMapper();


	public static String getProductJson() throws JsonProcessingException {

		return mapper.writeValueAsString(getProduct());
	}

	public static Product getProduct(){

		Product product = new Product();

		product.setPrice(27.1);
		product.setName("book");
		product.setDescription("fantasy books");
		product.setCurrency("eur");

		return product;
	}

}
