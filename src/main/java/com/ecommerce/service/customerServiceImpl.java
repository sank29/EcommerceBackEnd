package com.ecommerce.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.SessionDao;
import com.ecommerce.exception.CustomerException;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.Customer;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	SessionDao sessionDao;

	@Override
	public Customer createCustomer(Customer customer) throws CustomerException {
		
		Customer databaseCustomer = customerDao.findByMobileNo(customer.getMobileNo());
		
		if(databaseCustomer == null) {
			
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

}
















