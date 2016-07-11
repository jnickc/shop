package com.shop.service.utils;

import com.shop.service.exception.ProductCurrencyException;
import com.shop.entity.Product;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for CurrencyUtils class
 */
public class CurrencyUtilsTest {


	CurrencyUtils currencyUtils;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp(){

		currencyUtils = new CurrencyUtils();
	}

	/**
	 * Test product with default and non-default currency
	 * @throws Exception
	 */
	@Test
	public void isDefaultCurrency() throws Exception {

		Product product = new Product();

		product.setCurrency("eur");
		assertTrue(currencyUtils.isDefaultCurrency(product));

		product.setCurrency("usd");
		assertFalse(currencyUtils.isDefaultCurrency(product));

	}

	/**
	 * Tests product update to default currencyy
	 * @throws Exception
	 */
	@Test
	public void setPriceInDefaultCurrency() throws Exception {

		Product product = new Product();
		product.setCurrency("usd");
		product.setPrice(3d);

		CurrencyUtils currencyUtilsMock = mock(CurrencyUtils.class);

		Map<String, Float> rates = new HashMap<String, Float>();
		rates.put("usd", 1.1f);

		when(currencyUtilsMock.queryRates()).thenReturn(rates);
		when(currencyUtilsMock.setPriceInDefaultCurrency(product)).thenCallRealMethod();

		product = currencyUtilsMock.setPriceInDefaultCurrency(product);

		assertEquals(0, Double.compare(3.3, formatPrice(product.getPrice())));


		exception.expect(ProductCurrencyException.class);
		currencyUtils.setPriceInDefaultCurrency(product);


	}


	/**
	 * Tests get currency list and assures
	 * that it throws exception in case of error
	 * @throws Exception
	 */
	@Test
	public void getCurrencyList() throws Exception {


		CurrencyUtils currencyUtilsMock = mock(CurrencyUtils.class);

		Map<String, Float> rates = new HashMap<String, Float>();
		rates.put("usd", 1.1f);
		rates.put("rub", 0.07f);

		when(currencyUtilsMock.queryRates()).thenReturn(rates);
		when(currencyUtilsMock.getCurrencyList()).thenCallRealMethod();

		Set<String> currencies = currencyUtilsMock.getCurrencyList();
		assertEquals(2, currencies.size());

		exception.expect(ProductCurrencyException.class);
		currencyUtils.getCurrencyList();

	}


	private static double formatPrice(double price ){

		DecimalFormat df = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

		return Double.valueOf(df.format(price));
	}
}