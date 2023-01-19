package com.ecommerce.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.AdminProductDao;
import com.ecommerce.dao.CustomerProductDao;
import com.ecommerce.dao.PaymentDao;
import com.ecommerce.exception.PaymentException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Product;


@Service
public class AdminProductServiceImpl implements AdminProductService {
	
	@Autowired
	AdminProductDao adminProductDao;
	
	@Autowired
	CustomerProductDao customerProductDao;
	
	@Autowired
	PaymentDao paymentDao;

	@Override
	public Product addProduct(Product product) throws ProductException {
		
		if(product != null) {
			
			Product databaseProduct = customerProductDao.findByNameInDataBase(product.getProductName());
			
			if(databaseProduct == null) {
				
				product.setProdcutStatusInCart(false);
//				product.setListOfCutomerBoughtThisProduct(null);
				
				
				return adminProductDao.save(product);
			
			}else {
				throw new ProductException("Product already register with this name " + product.getProductName());
			}
			
			
			
		
		}else {
			
			throw new ProductException("Please enter valid product");
			
		}
		
	}
	
	public List<Payment> getAllPaymentDeatils() throws PaymentException{
		
//		List<Payment> listOfPaymet = new ArrayList<>();
		
		List<Payment> listOfPaymet = paymentDao.findAll();
		
		if(!listOfPaymet.isEmpty()) {
			
			return listOfPaymet;
			
		}else {
			
			throw new PaymentException("There is no payment to show");
		}
		
	}
	
	
	
	
	

}
