package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.LoginException;
import com.ecommerce.exception.PaymentException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Product;
import com.ecommerce.service.AdminProductService;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.LoginService;

@RestController
public class AdminProductController {
	
	@Autowired
	AdminProductService adminProductService;
	
	@Autowired
	LoginService loginService;
	
	
	@Autowired
	CustomerService customerService;
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestParam String key, @RequestBody Product product) throws ProductException, LoginException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			CurrentUserSession currentUserSession = customerService.getCurrentUserByUuid(key);
			
			if(!currentUserSession.getUserType().equals("admin")) {
				
				throw new LoginException("Please login as admin");
				
			}
			
			Product product2 = adminProductService.addProduct(product);
			
			if(product2 != null) {
				
				return new ResponseEntity<Product>(product2,HttpStatus.OK);
				
			}else {
				
				throw new ProductException("Please enter valid product");
				
			}
			
		}else {
			
			throw new LoginException("Invalid key or please login first");
			
		}
		
		
	}
	
	@GetMapping("/allPayments")
	public ResponseEntity<List<Payment>> getAllPaymentMadeByCustomer(@RequestParam String key) throws LoginException, PaymentException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			CurrentUserSession currentUserSession = customerService.getCurrentUserByUuid(key);
			
			if(!currentUserSession.getUserType().equals("admin")) {
				
				throw new LoginException("Please login as admin");
				
			}
			
			List<Payment> listOfPayment = adminProductService.getAllPaymentDeatils();
			
			return new ResponseEntity<>(listOfPayment, HttpStatus.OK);
			
		}else {
			
			throw new LoginException("Invalid key or please login first");
			
		}
	}
	
}
