package com.shop.service;

import com.shop.entity.Category;

import java.util.List;


/**
 * Service layer interface for category type
 * Contains all basic CRUD operations
 */
public interface CategoryService {

	/**
	 *  Saves a given category object.
	 *  Use returned object for further operations
	 * @param category
	 * @return saved entity
	 */
	Category save(Category category);

	/**
	 * Retrieves the category by it's id
	 * @param categoryId
	 * @return category entity with the given id or {@literal null} if none found
	 */
	Category findOne(Integer categoryId);

	/**
	 * Returns all categories
	 * @return list of categories
	 */
	List<Category> findAll();

	/**
	 * Deletes the category with given Id
	 * @param categoryId
	 */
	void delete(Integer categoryId);
}
