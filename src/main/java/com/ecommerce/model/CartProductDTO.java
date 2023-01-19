package com.ecommerce.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class CartProductDTO {
	
	
	private Integer productId;
	
	
	@Id
	private String productName;
	
	private Integer quantityBrougth;
	
	
	
	
	
}
