package com.ecommerce.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TotalBillInCart {
	
	@Id
	private Integer billReciptNumber;
	
	@ElementCollection
	private List<Product> listOfProductInCart;
	
	private Integer totalBill;
	
	@OneToOne
	private Payment payment;

	public TotalBillInCart() {
		super();
	}

	public TotalBillInCart(Integer billReciptNumber, List<Product> listOfProductInCart, Integer totalBill) {
		super();
		this.billReciptNumber = billReciptNumber;
		this.listOfProductInCart = listOfProductInCart;
		this.totalBill = totalBill;
	}

	public Integer getBillReciptNumber() {
		return billReciptNumber;
	}

	public void setBillReciptNumber(Integer billReciptNumber) {
		this.billReciptNumber = billReciptNumber;
	}

	public List<Product> getListOfProductInCart() {
		return listOfProductInCart;
	}

	public void setListOfProductInCart(List<Product> listOfProductInCart) {
		this.listOfProductInCart = listOfProductInCart;
	}

	public Integer getTotalBill() {
		return totalBill;
	}

	public void setTotalBill(Integer totalBill) {
		this.totalBill = totalBill;
	}
	
	

}
