package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.TotalBillInCart;


public interface TotalBillInCartDao extends JpaRepository<TotalBillInCart, Integer> {

}
