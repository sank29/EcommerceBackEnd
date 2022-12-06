package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Payment;

public interface PaymentDao extends JpaRepository<Payment, Integer> {

}
