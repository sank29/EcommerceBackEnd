package com.ecommerce.service;


import java.util.List;

import com.ecommerce.exception.PaymentException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Product;

public interface AdminProductService {
	
	public Product addProduct(Product product) throws ProductException;
	
	public List<Payment> getAllPaymentDeatils() throws PaymentException;

}
