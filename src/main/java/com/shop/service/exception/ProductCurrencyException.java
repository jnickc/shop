package com.shop.service.exception;


/**
 * Custom exception used in save/update product
 */
public class ProductCurrencyException extends RuntimeException {

	public ProductCurrencyException(String message){
		super(message);
	}

	public ProductCurrencyException(String message, Throwable throwable){
		super(message, throwable);
	}
}
