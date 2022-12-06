package com.ecommerce.service;

import java.util.List;

import com.ecommerce.exception.CartException;
import com.ecommerce.exception.CustomerException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.exception.TotalBillInCartException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Product;
import com.ecommerce.model.TotalBillInCart;

public interface CustomerProductService {
	
	public List<Product> findAllProductInDataBase() throws ProductException;
	
	public Product getProductByProductName(String productName) throws ProductException;
	
	public Product buyProductAndAddToCart(Product product, Customer customer) throws ProductException;
	
	public TotalBillInCart getTotalBillInCartForPayment(Customer customer) throws ProductException, CustomerException, CartException;
	
	public Payment makePayment(Customer customer, String modeOfPayment) throws ProductException, CustomerException, CartException, TotalBillInCartException;
	
}
