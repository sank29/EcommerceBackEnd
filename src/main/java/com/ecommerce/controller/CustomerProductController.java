package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.LoginException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Product;
import com.ecommerce.service.LoginService;
import com.ecommerce.service.CustomerProductService;

@RestController
public class CustomerProductController {
	
	@Autowired
	CustomerProductService customerProductService;
	
	@Autowired
	LoginService loginService;
	
	
	@GetMapping("/product/name")
	public ResponseEntity<Product> searchProductByName(@RequestParam String key, @PathVariable String name) throws LoginException, ProductException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			Product product =  customerProductService.getProductByProductName(name);
			
			return new ResponseEntity<Product>(product, HttpStatus.OK);
			
			
		}else {
			
			throw new LoginException("Invalid key or please login first");
		}
		
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> searchAllProduct(@RequestParam String key) throws ProductException, LoginException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			List<Product> allProductList =  customerProductService.findAllProductInDataBase();
			
			if(allProductList.size() != 0) {
				
				
				return new ResponseEntity<List<Product>>(allProductList,HttpStatus.OK);
				
			}else {
				
				throw new ProductException("No products to show");
			}
		}else {
			
			throw new LoginException("Invalid key or please login first");
		}
		
		
	}

}
