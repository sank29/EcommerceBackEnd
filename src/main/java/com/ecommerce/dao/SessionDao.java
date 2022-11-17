package com.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.currentUserSession;

public interface SessionDao extends JpaRepository<currentUserSession, Integer> {

}
