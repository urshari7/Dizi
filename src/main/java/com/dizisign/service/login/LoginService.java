package com.dizisign.service.login;

import com.dizisign.model.user.DiziUser;

/**
 * This is the single interface for Login functionality
 *
 */
public interface LoginService {

	public DiziUser login(String email,String passwordHash);
	
	public boolean isLoggedIn(String email);
	
	public boolean logout(String email);
	
}
