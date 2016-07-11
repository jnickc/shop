package com.shop.service.impl;

import com.shop.entity.Product;
import com.shop.service.ProductService;
import com.shop.service.utils.CurrencyUtils;
import com.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	ProductRepository productRepository;

	@Autowired
	CurrencyUtils currencyUtils;


	public Product save(Product product) {

		if(!currencyUtils.isDefaultCurrency(product)){
			product = currencyUtils.setPriceInDefaultCurrency(product);
		}

		return productRepository.save(product);
	}

	public Product findOne(Integer productId) {
		return productRepository.findOne(productId);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void delete(Integer productId) {
		productRepository.delete(productId);
	}

	@Override
	public Set<String> getRatesList() {

		return currencyUtils.getCurrencyList();
	}
}
