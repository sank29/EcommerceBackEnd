package com.ecommerce.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentId;
	
	private LocalDateTime localDateTime;
	
	@ManyToOne
	@JsonIgnore
	private Customer customer;
	
	private String modeOfPayment;
	
	private Boolean paymentStatus = false;
	
	@OneToOne
	private TotalBillInCart totalBillInCart;
	
	
	private Integer TotalBillPaid;

	public Payment() {
		super();
	}

	public Payment(Integer paymentId, LocalDateTime localDateTime, Customer customer, String modeOfPayment,
			Boolean paymentStatus) {
		super();
		this.paymentId = paymentId;
		this.localDateTime = localDateTime;
		this.customer = customer;
		this.modeOfPayment = modeOfPayment;
		this.paymentStatus = paymentStatus;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public Boolean getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public TotalBillInCart getTotalBillInCart() {
		return totalBillInCart;
	}

	public void setTotalBillInCart(TotalBillInCart totalBillInCart) {
		this.totalBillInCart = totalBillInCart;
	}

	public Integer getTotalBillPaid() {
		return TotalBillPaid;
	}

	public void setTotalBillPaid(Integer totalBillPaid) {
		TotalBillPaid = totalBillPaid;
	}
	
	
	
}




























