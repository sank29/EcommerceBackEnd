package com.ecommerce.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
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
public class TotalBillInCart {
	
	@Id
	private Integer billReciptNumber;
	
	@ElementCollection
	private List<Product> listOfProductInCart;
	
	private Integer totalBill;
	
	@OneToOne
	private Payment payment;


	

}
