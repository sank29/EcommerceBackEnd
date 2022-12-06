package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.CartException;
import com.ecommerce.exception.CustomerException;
import com.ecommerce.exception.LoginException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.exception.TotalBillInCartException;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Product;
import com.ecommerce.model.TotalBillInCart;
import com.ecommerce.service.LoginService;
import com.ecommerce.service.CustomerProductService;
import com.ecommerce.service.CustomerService;

@RestController
public class CustomerProductController {
	
	@Autowired
	CustomerProductService customerProductService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	CustomerService customerService;
	
	
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
	
	@PostMapping("/addtocart")
	public ResponseEntity<Product> addToCart(@RequestParam String key, @RequestBody Product product) throws ProductException, LoginException, CustomerException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			Customer customer = customerService.getCustomerByUuid(key);
			
			Product databaseProduct = customerProductService.buyProductAndAddToCart(product, customer);
			
			return new ResponseEntity<Product>(databaseProduct,HttpStatus.ACCEPTED);
			
		}else {
			
			throw new LoginException("Invalid key or please login first");
			
		}
		
		
	}
	
	@GetMapping("/cart/bill")
	public ResponseEntity<TotalBillInCart> calculateTotalBillInCart(@RequestParam String key) throws ProductException, CustomerException, CartException, LoginException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			Customer customer = customerService.getCustomerByUuid(key);
			
			if(customer != null) {
				
				TotalBillInCart totalBillInCart = customerProductService.getTotalBillInCartForPayment(customer);
				
				return new ResponseEntity<TotalBillInCart>(totalBillInCart, HttpStatus.CREATED);
				
			}else {
				
				throw new CustomerException("Please enter valid uuid key " + key);
				
			}
			
		}else {
			
			throw new LoginException("Invalid key or please login first");
			
		}
	}
	
	@PostMapping("/makePayment/{modeOfPaymet}")
	public ResponseEntity<Payment> makePayment(@RequestParam String key,@PathVariable String modeOfPaymet) throws ProductException, CustomerException, CartException, TotalBillInCartException, LoginException{
		
		if(loginService.checkUserLoginOrNot(key)) {
			
			Customer customer = customerService.getCustomerByUuid(key);
			
			if(customer != null) {
				
				Payment payment = customerProductService.makePayment(customer, modeOfPaymet);
				
				return new ResponseEntity<Payment>(payment,HttpStatus.OK);
				
			}else {
				
				throw new CustomerException("Please enter valid uuid key " + key);
				
			}
			
		}else {
			
			throw new LoginException("Invalid key or please login first");
			
		}
	}
	
	

}































