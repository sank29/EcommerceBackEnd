package com.ecommerce.service;

import com.ecommerce.exception.LoginException;
import com.ecommerce.model.LoginDTO;

public interface loginService {
	
	public String logIntoAccount(LoginDTO loginDTO) throws LoginException;
	
	public String logoutFromAccount(String key) throws LoginException;

}
