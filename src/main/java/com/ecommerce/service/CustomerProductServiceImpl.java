package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.CustomerProductDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{

	@Autowired
	CustomerProductDao productDao;
	
	@Override
	public List<Product> findAllProductInDataBase() throws ProductException {
		
		List<Product> listOfAllProduct =  productDao.findAll();
		
		if(listOfAllProduct.size() != 0) {
			return listOfAllProduct;
	
		}else {
			
			throw new ProductException("No product available right now");
			
		}
		
	}
	
	public Product getProductByProductName(String productName) throws ProductException {
		
		Product product = productDao.findByNameInDataBase(productName);
		
		if(product != null) {
			
			
			return product;
	
		}else {
			
			throw new ProductException("There is no product base on this name " + productName);
		}
	}

}













