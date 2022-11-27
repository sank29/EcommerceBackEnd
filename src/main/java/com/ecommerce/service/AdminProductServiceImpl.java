package com.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.AdminProductDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;


@Service
public class AdminProductServiceImpl implements AdminProductService {
	
	@Autowired
	AdminProductDao adminProductDao;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		if(product != null) {
			
			adminProductDao.save(product);
			
			return product;
		
		}else {
			
			throw new ProductException("Please enter valid product");
			
		}
		
	}
	
	
	
	
	

}
