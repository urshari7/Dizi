package com.dizisign.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.dizisign.dao.LoginDAO;
import com.dizisign.dao.UserDAO;
import com.dizisign.dao.repository.UserRepository;
import com.dizisign.model.user.DiziUser;

public class RegisterAccountServiceImpl implements RegisterAccountService {
	
	@Autowired
	private UserRepository userRepository;

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public DiziUser register(DiziUser user) {
		// TODO Auto-generated method stub
		System.err.println(user.toString());
		System.err.println("in user reository");
		return userRepository.save(user);
		
	}

}
