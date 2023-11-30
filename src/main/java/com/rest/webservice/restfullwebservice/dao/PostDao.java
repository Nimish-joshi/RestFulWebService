package com.rest.webservice.restfullwebservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.webservice.restfullwebservice.entity.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

	

}
