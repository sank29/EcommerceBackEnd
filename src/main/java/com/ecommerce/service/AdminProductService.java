package com.ecommerce.service;


import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;

public interface AdminProductService {
	
	public Product addProduct(Product product) throws ProductException;

}
