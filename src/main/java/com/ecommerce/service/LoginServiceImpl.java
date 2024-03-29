package com.ecommerce.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.SessionDao;
import com.ecommerce.exception.LoginException;
import com.ecommerce.model.Cart;
import com.ecommerce.model.CurrentUserSession;
import com.ecommerce.model.Customer;
import com.ecommerce.model.LoginDTO;
import com.ecommerce.model.LoginUUIDKey;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	SessionDao sessionDao;
	
	@Autowired
	CartDao cartDao;
	
	@Override
	public LoginUUIDKey logIntoAccount(LoginDTO loginDTO) throws LoginException {
		
		LoginUUIDKey loginUUIDKey = new LoginUUIDKey();
		
		Customer existingCustomer = customerDao.findByMobileNo(loginDTO.getMobileNo());
		
		if(existingCustomer == null) {
			throw new LoginException("Please enter valid mobile number " + loginDTO.getMobileNo());
		}
		
		
		Optional<CurrentUserSession> validCustomerSessionOpt = sessionDao.findById(existingCustomer.getCustomerId());
		
		if(validCustomerSessionOpt.isPresent()) {
			
			throw new LoginException("User already login");
			
		}
		
		if(existingCustomer.getPassword().equals(loginDTO.getPassword())) {
			
			String key = RandomString.make(10);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(), key, LocalDateTime.now());
			
			if(existingCustomer.getPassword().equals("admin") && existingCustomer.getMobileNo().equals("1234567890")) {
				
				existingCustomer.setType("admin");
				currentUserSession.setUserType("admin");
				currentUserSession.setUserId(existingCustomer.getCustomerId());
				
				sessionDao.save(currentUserSession);
				customerDao.save(existingCustomer);
				
				loginUUIDKey.setMsg("Login Successful as admin with key");
				
				loginUUIDKey.setUuid(key);
				
				return loginUUIDKey;
				
				
			}else {
				
				existingCustomer.setType("customer");
				currentUserSession.setUserId(existingCustomer.getCustomerId());
				currentUserSession.setUserType("customer");
				
			
				
			}
			
			customerDao.save(existingCustomer);
			
			sessionDao.save(currentUserSession);
			
			loginUUIDKey.setMsg("Login Successful as customer with this key");
			
			loginUUIDKey.setUuid(key);
			
			return loginUUIDKey;
		
		}else {
			
			throw new LoginException("Please enter valid password");
			
		}
		
	}

	@Override
	public String logoutFromAccount(String key) throws LoginException {
		
		CurrentUserSession currentSessionOptional = sessionDao.findByUuid(key);
		
		if(currentSessionOptional != null) {
			
			sessionDao.delete(currentSessionOptional);
			
			return "Logout successful";
			
		}else {
			
			throw new LoginException("Please enter valid key");
			
		}
		
		
	}

	@Override
	public Boolean checkUserLoginOrNot(String key) throws LoginException {
		
		CurrentUserSession currentUserSession = sessionDao.findByUuid(key);
		
		if(currentUserSession != null) {
			
			return true;
			
		}else {
			
			return false;
			
		}
		
		
		
	}

}
