package com.ecommerce.service;

import com.ecommerce.exception.LoginException;
import com.ecommerce.model.LoginDTO;
import com.ecommerce.model.LoginUUIDKey;

public interface LoginService {
	
	public LoginUUIDKey logIntoAccount(LoginDTO loginDTO) throws LoginException;
	
	public String logoutFromAccount(String key) throws LoginException;
	
	public Boolean checkUserLoginOrNot(String key) throws LoginException;

}
