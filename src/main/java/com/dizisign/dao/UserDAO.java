package com.dizisign.dao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dizisign.model.user.DiziUser;

@Repository
public interface UserDAO extends CrudRepository<DiziUser, Long> {

}
