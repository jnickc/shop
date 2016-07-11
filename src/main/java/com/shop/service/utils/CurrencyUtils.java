package com.shop.service.utils;

import com.shop.service.exception.ProductCurrencyException;
import com.shop.service.model.Currency;
import com.shop.entity.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

/**
 * Class helper for working with currencies
 */
@Component
public class CurrencyUtils {

	private static final Logger LOGGER = Logger.getLogger(CurrencyUtils.class);

	private static final String DEFAULT_CURRENCY = "EUR";

	private static final String EXCHANGE_RATES_URL = "http://api.fixer.io/latest";

	@Autowired
	RestTemplate restTemplate;

	/**
	 * Checks if product is in default currency
	 * @param product
	 * @return true if in default, otherwise false
	 */
	public boolean isDefaultCurrency(Product product) {
		return product.getCurrency().equalsIgnoreCase(DEFAULT_CURRENCY);
	}


	/**
	 * Changes product currency to default
	 * @param product
	 * @return product in default curreny
	 */
	public Product setPriceInDefaultCurrency(Product product) {

		Map<String, Float> rates = queryRates();

		try {

			float rate = rates.get(product.getCurrency());

			product.setPrice(product.getPrice() * rate);

		} catch (Exception ex) {
			LOGGER.error("Error in saving product, can't get currencies ", ex);

		}

		return product;
	}

	/**
	 * Returns list of currencies
	 * @return set of currencies
	 */
	public Set<String> getCurrencyList() {

		Map<String, Float> rates = queryRates();

		if (rates.size() == 0) {
			rates.put(DEFAULT_CURRENCY, 1.0f);
		}

		return rates.keySet();
	}

	/**
	 * Makes request to exchange to get list of rates
	 * @return rates map currency:rate
	 */
	protected Map<String, Float> queryRates() {

		try {

			Currency currency = restTemplate.getForObject(EXCHANGE_RATES_URL, Currency.class);

			return currency.getRates();
		} catch (Exception ex) {
			LOGGER.error("Error in getting currencies ", ex);
			throw new ProductCurrencyException("Error in getting currencies ", ex);
		}

	}
}


