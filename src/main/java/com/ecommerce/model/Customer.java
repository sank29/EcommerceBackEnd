package com.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	private String name;
	private String mobileNo;
	private String password;
	private String email;
	
	private String type;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Product> listOfProductBoughtByCustomer;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;
	
	public Customer() {
		
	}

	public Customer(Integer customerId, String name, String mobileNo, String password, String email, String type,
			List<Product> listOfProductBoughtByCustomer, Cart cart) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.password = password;
		this.email = email;
		this.type = type;
		this.listOfProductBoughtByCustomer = listOfProductBoughtByCustomer;
		this.cart = cart;
	}


	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public List<Product> getListOfProductBoughtByCustomer() {
		return listOfProductBoughtByCustomer;
	}



	public void setListOfProductBoughtByCustomer(List<Product> listOfProductBoughtByCustomer) {
		this.listOfProductBoughtByCustomer = listOfProductBoughtByCustomer;
	}





	public Cart getCart() {
		return cart;
	}





	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	

}
