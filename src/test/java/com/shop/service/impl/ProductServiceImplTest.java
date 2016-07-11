package com.shop.service.impl;

import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.utils.CurrencyUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit tests for product save operation
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	@InjectMocks
	ProductServiceImpl productService;

	@Mock
	ProductRepository productRepository;

	@Mock
	CurrencyUtils currencyUtils;

	private Product eurProduct;

	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);

		Product eurProduct = new Product();

		eurProduct.setCurrency("eur");
		eurProduct.setPrice(25.2);
	}

	@Test
	public void saveUsd() throws Exception {

		Product usdProduct = new Product();

		usdProduct.setCurrency("usd");
		usdProduct.setPrice(27.2);

		when(productRepository.save(eurProduct)).thenReturn(eurProduct);

		when(currencyUtils.isDefaultCurrency(usdProduct)).thenReturn(false);
		when(currencyUtils.setPriceInDefaultCurrency(usdProduct)).thenReturn(eurProduct);

		assertEquals(eurProduct, productService.save(usdProduct));
	}

	@Test
	public void saveEur() {
		when(productRepository.save(eurProduct)).thenReturn(eurProduct);
		when(currencyUtils.isDefaultCurrency(eurProduct)).thenReturn(true);

		assertEquals(eurProduct, productService.save(eurProduct));
	}
}
