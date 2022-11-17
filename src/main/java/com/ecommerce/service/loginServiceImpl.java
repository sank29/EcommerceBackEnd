package com.ecommerce.service;

import org.springframework.stereotype.Service;

import com.ecommerce.exception.LoginException;
import com.ecommerce.model.LoginDTO;


@Service
public class loginServiceImpl implements loginService {

	@Override
	public String logIntoAccount(LoginDTO loginDTO) throws LoginException {
		
		return null;
	}

	@Override
	public String logoutFromAccount(String key) throws LoginException {
		
		return null;
	}

}
