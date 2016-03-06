package com.dizisign.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.dizisign.dao.LoginDAO;

public class LoginServiceImpl implements LoginService {
	
	private LoginDAO loginDAO;
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	@Autowired
	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	@Override
	public boolean login(String email, String passwordHash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLoggedIn(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
