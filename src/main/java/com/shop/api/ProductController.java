package com.shop.api;

import com.shop.entity.Product;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


/**
 * Rest API for product entity
 * Interface with all basic CRUD operations
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {
	
	@Autowired
	ProductService productService;


	/**
	 * Saves a given product object
	 * @param product
	 * @return saved entity
	 */
	@RequestMapping(value="/save", method = RequestMethod.POST,
			produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Product save(@Validated @RequestBody Product product) {

		return productService.save(product);
	}

	/**
	 * Returns all products
	 * @return list of products
	 */
	@RequestMapping(value = "/findAll", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Product> findAll() {

		return productService.findAll();
	}


	/**
	 * Retrieves product by it's Id
	 * @param productId
	 * @return product with the given id or {@literal null} if none found
	 */
	@RequestMapping(value = "/findOne/{productId}", method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public Product findOne(@PathVariable("productId") Integer productId) {

		return productService.findOne(productId);
	}


	/**
	 * Deletes the product with the given Id
	 * @param productId
	 */
	@RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
	public void remove(@PathVariable("productId") Integer productId) {

		productService.delete(productId);
	}


	/**
	 * Gets list of rates available for product
	 * @return list of currencies
	 */
	@RequestMapping(value = "/getRatesList", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Set<String> getRatesList() {

		return productService.getRatesList();
	}

}
