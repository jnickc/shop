package com.shop.service;

import com.shop.entity.Product;

import java.util.List;
import java.util.Set;



/**
 * Service layer interface for category type
 * Contains all basic CRUD operations
 */
public interface ProductService {


	/**
	 * Saves a given product object
	 * @param product
	 * @return saved entity
	 */
	Product save(Product product);


	/**
	 * Retrieves product by it's Id
	 * @param productId
	 * @return product with the given id or {@literal null} if none found
	 */
	Product findOne(Integer productId);

	/**
	 * Returns all products
	 * @return list of products
	 */
	List<Product> findAll();

	/**
	 * Deletes the product with the given Id
	 * @param productId
	 */
	void delete(Integer productId);

	/**
	 * Gets list of currencies available for product
	 * @return list of currencies
	 */
	Set<String> getRatesList();
}
