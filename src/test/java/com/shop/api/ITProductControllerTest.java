package com.shop.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.Main;
import com.shop.repository.ProductRepository;
import com.shop.utils.ProductUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;



/**
 * Integration tests of rest API for product entity
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest
public class ITProductControllerTest {

	private static final String PRODUCT_API_URL = "/api/product";

	@Autowired
	ProductRepository productRepository;

	@Before
	public void setUp() {

		productRepository.deleteAll();
	}

	@Test
	public void testSave() throws JsonProcessingException {

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.contentType("application/json")
				.and()
				.body(ProductUtils.getProductJson())
				.when()
				.post(PRODUCT_API_URL+"/save").
				then().
				statusCode(HttpStatus.SC_OK)
				.body("name", is("book"))
				.body("description", is("fantasy books"));
	}

	@Test
	public void testFindOne() {
		productRepository.save(ProductUtils.getProduct());

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.get(PRODUCT_API_URL+"/findOne/{id}", 4)
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", is("book"))
				.body("currency", is("eur"));
	}

	@Test
	public void testFindAll() {
		productRepository.save(ProductUtils.getProduct());
		productRepository.save(ProductUtils.getProduct());

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.get(PRODUCT_API_URL+"/findAll")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("product.size()", is(2));
	}


	@Test
	public void testDelete() {
		productRepository.save(ProductUtils.getProduct());

		int productId = 6;

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.delete(PRODUCT_API_URL+"/delete/{id}", productId)
				.then()
				.statusCode(HttpStatus.SC_OK);

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.get(PRODUCT_API_URL+"/findAll")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("product.size()", is(0));
	}
}