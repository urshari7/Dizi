package com.dizisign.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.dizisign.dao.LoginDAO;
import com.dizisign.model.user.DiziUser;

public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	public LoginDAO getLoginDAO() {
		return loginDAO;
	}

	public void setLoginDAO(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	@Override
	public DiziUser login(String email, String passwordHash) {
		System.out.println("loginDAO:"+loginDAO);
		DiziUser user = loginDAO.findByEmail(email);
		if (user!=null){
			return passwordHash.equals(user.getPasswordHash())?user:null;
		}
		return null;
		
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
