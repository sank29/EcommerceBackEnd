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

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor

@Data
@Getter
@Setter
public class Product {
	
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer productid;
	
	@Id
	private String productName;
	
	
	private String type;
	private Integer quantity;
	private double price;
	private String productImg;
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	List<Customer> listOfCutomerBoughtThisProduct;
	
	@ManyToOne
	@JsonIgnore
	private Cart cart;
	
	private Boolean prodcutStatusInCart = false;
	
}















