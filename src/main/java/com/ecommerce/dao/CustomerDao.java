package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
}
