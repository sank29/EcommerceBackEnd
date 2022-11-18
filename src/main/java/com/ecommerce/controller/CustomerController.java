package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.CustomerException;
import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/registerCustomer")
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) throws CustomerException{
		
		Customer savedCustomer = customerService.createCustomer(customer);
		
		return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
		
	}
	
	
	@PutMapping("/updateCustomer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @RequestParam(required = false) String key) throws CustomerException{
		
		Customer updatedCustomer = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
		
	}
	
	
}
