package com.shop.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.repository.CategoryRepository;
import com.shop.utils.CategoryUtils;
import com.shop.Main;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;


/**
 * Integration tests of rest API for category entity
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
@IntegrationTest
public class ITCategoryControllerTest {


	private static final String CATEGORY_API_URL = "/api/category";

	@Autowired
	CategoryRepository categoryRepository;


	@Value("${local.server.port}")
	int port;

	@Before
	public void setUp() {

		categoryRepository.deleteAll();
	}

	@Test
	public void testSave() throws JsonProcessingException {

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.contentType("application/json")
				.and()
				.body(CategoryUtils.getCategoryJson())
				.when()
				.post(CATEGORY_API_URL + "/save")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", is("computers"))
				.body("description", is("Computer description"));
	}

	@Test
	public void testFindOne() {
		categoryRepository.save(CategoryUtils.getCategory());

		int categoryId = 5;
		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.get(CATEGORY_API_URL + "/findOne/{id}", categoryId)
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("name", is("computers"))
				.body("description", is("Computer description"));
	}

	@Test
	public void testFindAll() {
		categoryRepository.save(CategoryUtils.getCategory());
		categoryRepository.save(CategoryUtils.getCategory());

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.get(CATEGORY_API_URL + "/findAll")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("category.size()", is(2));
	}


	@Test
	public void testDelete() {
		categoryRepository.save(CategoryUtils.getCategory());

		int categoryId = 7;

		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.delete(CATEGORY_API_URL + "/delete/{id}", categoryId)
				.then()
				.statusCode(HttpStatus.SC_OK);
		given()
				.auth()
				.basic("admin", "admin")
				.when()
				.get(CATEGORY_API_URL + "/findAll")
				.then()
				.statusCode(HttpStatus.SC_OK)
				.body("category.size()", is(0));
	}
}