package com.shop.api;

import com.shop.entity.Category;
import com.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Rest API for category object
 * with implementation of all CRUD operations
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {


	@Autowired
	CategoryService categoryService;


	/**
	 *  Saves a given category object.
	 *  Use returned object for further operations
	 * @param category
	 * @return saved entity
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public Category save(@RequestBody Category category) {

		return categoryService.save(category);
	}

	/**
	 * Returns all categories
	 * @return list of categories
	 */
	@RequestMapping(value = "/findAll")
	public List<Category> findAll() {

		return categoryService.findAll();
	}


	/**
	 * Retrieves the category by it's id
	 * @param categoryId
	 * @return category entity with the given id or {@literal null} if none found
	 */
	@RequestMapping(value = "/findOne/{categoryId}", method = RequestMethod.GET)
	public Category findOne(@PathVariable("categoryId") Integer categoryId) {

		return categoryService.findOne(categoryId);
	}


	/**
	 * Deletes the category with given Id
	 * @param categoryId
	 */
	@RequestMapping(value = "/delete/{categoryId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("categoryId") Integer categoryId) {

		categoryService.delete(categoryId);
	}
	
}
