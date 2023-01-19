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
public class Cart {
	
	
	/// ??? auto generate required or not ????
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cartIdOrCustomerId;
	
//	@ManyToMany(cascade = CascadeType.ALL)
	@ElementCollection
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> listOfProductsInCart;
	
	
	@OneToOne
	private Customer customer;

	@ManyToMany
	private List<CartProductDTO> listOfProductInCart1;
	
}
