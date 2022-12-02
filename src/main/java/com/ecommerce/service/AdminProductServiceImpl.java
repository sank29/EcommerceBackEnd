package com.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.AdminProductDao;
import com.ecommerce.dao.CustomerProductDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;


@Service
public class AdminProductServiceImpl implements AdminProductService {
	
	@Autowired
	AdminProductDao adminProductDao;
	
	@Autowired
	CustomerProductDao customerProductDao;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		if(product != null) {
			
			Product databaseProduct = customerProductDao.findByNameInDataBase(product.getProductName());
			
			if(databaseProduct == null) {
				
				product.setProdcutStatusInCart(false);
				product.setListOfCutomerBoughtThisProduct(null);
				
				
				return adminProductDao.save(product);
			
			}else {
				throw new ProductException("Product already register with this name " + product.getProductName());
			}
			
			
			
		
		}else {
			
			throw new ProductException("Please enter valid product");
			
		}
		
	}
	
	
	
	
	

}
