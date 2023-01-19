package com.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.AdminProductDao;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.CustomerProductDao;
import com.ecommerce.dao.PaymentDao;
import com.ecommerce.dao.TotalBillInCartDao;
import com.ecommerce.exception.CartException;
import com.ecommerce.exception.CustomerException;
import com.ecommerce.exception.ProductException;
import com.ecommerce.exception.TotalBillInCartException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Payment;
import com.ecommerce.model.Product;
import com.ecommerce.model.TotalBillInCart;

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
	
	@Autowired
	TotalBillInCartDao totalBillInCartDao;
	
	@Autowired
	PaymentDao paymentDao;
	
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
		
		Optional<Product> databaseProduct = customerProductDao.findById(product.getProductName());
		
		
		if(databaseProduct.isPresent()) {
			
			// check if produc is already added in cart if yes then only add the quantity and then save
			
			Boolean flag  = false;
			
			List<Product> listOfProduct = customer.getCart().getListOfProductsInCart();
			
			for(Product eachProduct: listOfProduct) {
				
				if(eachProduct.getProductName().equals(databaseProduct.get().getProductName())) {
					
					flag = true;
					
				}
				
			} 
			
			if(databaseProduct.get().getQuantity() > product.getQuantity()) {
				
				if(flag) {
					
					for(Product eachProduct: listOfProduct) {
						
						if(eachProduct.getProductName().equals(databaseProduct.get().getProductName())) {
							int quantity = product.getQuantity();
							int presentQuntity = eachProduct.getQuantity();
							
							eachProduct.setQuantity(quantity + presentQuntity);
							
						}
						
					} 
					
					customer.getCart().setListOfProductsInCart(listOfProduct); 
					
					
					customerDao.save(customer);
					
				}else {
					
					product.setPrice(databaseProduct.get().getPrice());
					
					product.setProductName(databaseProduct.get().getProductName());
					
					product.setType(databaseProduct.get().getType());
					
					product.setQuantity(product.getQuantity());
					
//					System.out.println("*****");
//					customer.getListOfProductBoughtByCustomer().forEach(eachProduct -> eachProduct.getListOfCarts().add(customer.getCart()));
//					System.out.println("*****");
					
					customer.getCart().getListOfProductsInCart().add(product);
					
					
					customer.getCart().setCustomer(customer);
					
					databaseProduct.get().setQuantity(databaseProduct.get().getQuantity() - product.getQuantity());
					 
					customerDao.save(customer);
					
					adminProductDao.save(databaseProduct.get());// this line is overriding the existing data in cart product added.
					
					
				}
				
				
				
				return product;
				
			}else {
				
				throw new ProductException("You can't buy this product quantity is less in your database");
				
			}
			
			
			
		}else {
			
			throw new ProductException("Product not found with this product id " + product.getProductid());
		}
		
	}

	@Override
	public TotalBillInCart getTotalBillInCartForPayment(Customer customer) throws ProductException, CustomerException, CartException {
		
		if(customer != null) {
			
			List<Product> listOfProductInCustomerCart = customer.getCart().getListOfProductsInCart();
			
			System.out.println("*******");
			System.out.println(listOfProductInCustomerCart);
			System.out.println("*******");
			
			if(listOfProductInCustomerCart.size() != 0) {
				
				int totalBill = 0;
				
				for (Product product : listOfProductInCustomerCart) {
					
					totalBill += product.getPrice() * product.getQuantity();

				}
				
				TotalBillInCart totalBillInCart = new TotalBillInCart();
				
				totalBillInCart.setBillReciptNumber(customer.getCustomerId());
				totalBillInCart.setListOfProductInCart(listOfProductInCustomerCart);
				totalBillInCart.setTotalBill(totalBill);
				
				totalBillInCartDao.save(totalBillInCart);
				
				return totalBillInCart;
				
				
			}else {
				
				throw new CartException("Cart is empty. Please add product for payment");
			}
			
		}else {
			
			throw new CustomerException("Please enter valid customer details");
		}
		
	}
	
	public Payment makePayment(Customer customer, String modeOfPayment)throws ProductException, CustomerException, CartException, TotalBillInCartException {
		
		if(customer != null) {
			
			if(!customer.getCart().getListOfProductsInCart().isEmpty()) {
				
				// find out bill bill receipt exists or not... bill receipt number is equals to customer id
				
				Optional<TotalBillInCart> totalBillInCart = totalBillInCartDao.findById(customer.getCustomerId());
				
				if(totalBillInCart.isPresent()) {
					
					totalBillInCart.get().getTotalBill();
					
					Payment payment = new Payment();
					
					payment.setCustomer(customer);
					payment.setLocalDateTime(LocalDateTime.now());
					payment.setModeOfPayment(modeOfPayment);
					payment.setPaymentStatus(true);
					payment.setTotalBillPaid(totalBillInCart.get().getTotalBill());
					payment.setTotalBillInCart(totalBillInCart.get());
					
					customer.setListOfProductBoughtByCustomer(customer.getCart().getListOfProductsInCart());
					customer.getCart().setListOfProductsInCart(new ArrayList<>());
					
					
					return paymentDao.save(payment);
					
				}else {
					
					throw new TotalBillInCartException("Please make bill from cart to buy products or please add proudcts in cart");
				}
				
				
				
			}else {
				
				throw new CartException("No product found in cart");
			}
			
		}else {
			
			throw new CustomerException("Please enter valid customer details");
		}
		
	}
	
	

}













