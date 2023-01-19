package com.ecommerce.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
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

	
	
	
	
}




























