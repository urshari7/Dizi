package com.dizisign.dao;

import org.springframework.stereotype.Repository;

import com.dizisign.dao.repository.ReadOnlyRepository;
import com.dizisign.model.user.DiziUser;

@Repository
public interface LoginDAO extends ReadOnlyRepository<DiziUser, Long>{
	public DiziUser findByEmail(String email);
}
