package com.rest.webservice.restfullwebservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservice.restfullwebservice.entity.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer> {

	

}
