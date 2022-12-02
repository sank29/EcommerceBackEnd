package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.AdminProductDao;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.CustomerProductDao;
import com.ecommerce.exception.ProductException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;

@Service
public class CustomerProductServiceImpl implements CustomerProductService{

	@Autowired
	CustomerProductDao customerProductDao;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	AdminProductDao adminProductDao;
	
	@Override
	public List<Product> findAllProductInDataBase() throws ProductException {
		
		List<Product> listOfAllProduct =  customerProductDao.findAll();
		
		if(listOfAllProduct.size() != 0) {
			return listOfAllProduct;
	
		}else {
			
			throw new ProductException("No product available right now");
			
		}
		
	}
	
	public Product getProductByProductName(String productName) throws ProductException {
		
		Product product = customerProductDao.findByNameInDataBase(productName);
		
		if(product != null) {
			
			
			return product;
	
		}else {
			
			throw new ProductException("There is no product base on this name " + productName);
		}
	}

	@Override
	public Product buyProductAndAddToCart(Product product, Customer customer) throws ProductException {
		
		Optional<Product> databaseProduct = customerProductDao.findById(product.getProductid());
		
		if(databaseProduct.isPresent()) {
			
			
//			customer.getCart().setCartId(customer.getCustomerId());
			
			if(databaseProduct.get().getQuantity() > product.getQuantity()) {
				
				customer.getCart().getListOfProductsInCart().add(product);
				
				customer.getCart().setCustomer(customer);
				
				product.setQuantity(databaseProduct.get().getQuantity() - product.getQuantity());
				
				adminProductDao.save(product);
				
				
				
				customerDao.save(customer);
				
				System.out.println(customer.getCart().getListOfProductsInCart());
				
				return product;
				
			}else {
				throw new ProductException("You can't buy this product quantity is less in your database");
			}
			
			
			
		}else {
			
			throw new ProductException("Product not found with this product id " + product.getProductid());
		}
		
	}

}













