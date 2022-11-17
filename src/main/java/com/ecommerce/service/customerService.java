package com.ecommerce.service;

import com.ecommerce.exception.CustomerException;
import com.ecommerce.model.Customer;

public interface customerService {
	
	public Customer createCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(Customer customer) throws CustomerException;  

}
