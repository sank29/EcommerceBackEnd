package com.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Cart {
	
	
	/// ??? auto generate required or not ????
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartIdOrCustomerId;
	
//	@ManyToMany(cascade = CascadeType.ALL)
	@ElementCollection
	@ManyToMany
	private List<Product> listOfProductsInCart;
	
	
	@OneToOne()
	private Customer customer;

	public Cart() {
		super();
	}

	

	public Cart(Integer cartIdOrCustomerId, List<Product> listOfProductsInCart, Customer customer) {
		super();
		this.cartIdOrCustomerId = cartIdOrCustomerId;
		this.listOfProductsInCart = listOfProductsInCart;
		this.customer = customer;
	}



	public List<Product> getListOfProductsInCart() {
		return listOfProductsInCart;
	}

	public void setListOfProductsInCart(List<Product> listOfProductsInCart) {
		this.listOfProductsInCart = listOfProductsInCart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	public Integer getCartId() {
		return cartIdOrCustomerId;
	}



	public void setCartId(Integer cartIdOrCustomerId) {
		this.cartIdOrCustomerId = cartIdOrCustomerId;
	}

	
	
	
}
