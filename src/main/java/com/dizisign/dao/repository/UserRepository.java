package com.dizisign.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.dizisign.model.user.DiziUser;


public interface UserRepository extends CrudRepository<DiziUser, Long> {

}
