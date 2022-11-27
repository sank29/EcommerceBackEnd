package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Product;

@Repository
public interface CustomerProductDao extends JpaRepository<Product, Integer>{
	
	
	
	@Query("from Product p where p.productName = ?1")
	public Product findByNameInDataBase(String name);

}
