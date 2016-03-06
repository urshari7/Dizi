package com.dizisign.service.login;

/**
 * This is the single interface for Login functionality
 *
 */
public interface LoginService {

	public boolean login(String email,String passwordHash);
	
	public boolean isLoggedIn(String email);
	
	public boolean logout(String email);
	
}
