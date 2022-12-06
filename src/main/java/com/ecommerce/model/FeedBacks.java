package com.ecommerce.model;

import java.util.List;

import javax.persistence.ManyToMany;

public class FeedBacks {
	
	private String feedbacks;
	
	@ManyToMany
	private List<Customer> customer;
	
	

}
