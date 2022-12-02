package com.ecommerce.service;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.SessionDao;
import com.ecommerce.exception.CustomerException;
import com.ecommerce.exception.LoginException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	CartDao cartDao;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {
		
		Customer databaseCustomer = customerDao.findByMobileNo(customer.getMobileNo());
		
		if(databaseCustomer == null) {
			
			Cart cart = new Cart();
			
			cart.setCustomer(databaseCustomer);
			
			customer.setCart(cart);
			
			customerDao.save(customer);
			
			return customer;
			
		
		}else {	
			
			throw new CustomerException("Customer already register with this mobile no " + customer.getMobileNo());
			
		}
		
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		
		CurrentUserSession loggedInUser = sessionDao.findByUuid(key);
		
		if(loggedInUser == null) {
			
			throw new CustomerException("Please provide the valid key to update the customer");
		}
		
		if(customer.getCustomerId() == loggedInUser.getUserId()) {
			
			return customerDao.save(customer);
	
		}else {
			throw new CustomerException("Invalid customer details. Login first");
		}
		
	}

	@Override
	public CurrentUserSession getCurrentUserByUuid(String uuid) throws LoginException {
		
		CurrentUserSession currentUserSession = sessionDao.findByUuid(uuid);
		
		if(currentUserSession != null) {
			
			return currentUserSession;
			
		}else {
			
			throw new LoginException("Please enter valid key");
		}
	
	}

	@Override
	public Customer getCustomerByUuid(String uuid) throws CustomerException {
		
		CurrentUserSession customer = sessionDao.findByUuid(uuid);
		
		Optional<Customer> customers = customerDao.findById(customer.getUserId());
		
		if(customers.isPresent()) {
			
			return customers.get();
		
		}else {
			
			throw new CustomerException("Customer not present by this uuid " + uuid);
		}
		
	}

}
















