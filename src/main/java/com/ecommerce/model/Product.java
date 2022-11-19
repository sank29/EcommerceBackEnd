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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productid;
	
	private String productName;
	private String type;
	private Integer quantity;
	private double price;
	
	@ManyToMany(cascade = CascadeType.ALL)
	List<Customer> listOfCutomerBoughtThisProduct;
	
	@ManyToOne
	private Cart cart;
	
	
	private Boolean prodcutStatusInCart = false;
	
	


	public Product() {
		super();
	}

	

	public Product(Integer productid, String productName, String type, Integer quantity, double price,
			List<Customer> listOfCutomerBoughtThisProduct, Cart cart, Boolean prodcutStatusInCart) {
		super();
		this.productid = productid;
		this.productName = productName;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.listOfCutomerBoughtThisProduct = listOfCutomerBoughtThisProduct;
		this.cart = cart;
		this.prodcutStatusInCart = prodcutStatusInCart;
	}



	public Integer getProductid() {
		return productid;
	}


	public void setProductid(Integer productid) {
		this.productid = productid;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public List<Customer> getListOfCutomerBoughtThisProduct() {
		return listOfCutomerBoughtThisProduct;
	}


	public void setListOfCutomerBoughtThisProduct(List<Customer> listOfCutomerBoughtThisProduct) {
		this.listOfCutomerBoughtThisProduct = listOfCutomerBoughtThisProduct;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}


	public Boolean getProdcutStatusInCart() {
		return prodcutStatusInCart;
	}


	public void setProdcutStatusInCart(Boolean prodcutStatusInCart) {
		this.prodcutStatusInCart = prodcutStatusInCart;
	}
	
	
	
	

}
