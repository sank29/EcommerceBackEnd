package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.LoginException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.service.AdminProductService;
import com.ecommerce.service.LoginService;

@RestController
public class AdminProductController {
	
	@Autowired
	AdminProductService adminProductService;
	
	@Autowired
	LoginService loginService;
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestParam String key, @RequestBody Product product) throws ProductException, LoginException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
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
	
}
