package com.rest.webservice.restfullwebservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservice.restfullwebservice.dao.PostDao;
import com.rest.webservice.restfullwebservice.dao.UserDao;
import com.rest.webservice.restfullwebservice.entity.Post;
import com.rest.webservice.restfullwebservice.entity.Users;
import com.rest.webservice.restfullwebservice.exception.UserNotFoundException;

@RestController
public class UserJpaController {
	
	private UserDao userdao;
	
	private PostDao postdao;
	
	public UserJpaController(UserDao userdao,PostDao postdao) {
		this.userdao=userdao;
		this.postdao=postdao;
	}

	@GetMapping("/jpa/users")
	public List<Users> getAllUser(){
		return userdao.findAll();
	}
	
	@GetMapping("/jpa/user/{id}")
	public EntityModel<Users> getOneUserUsingEntityMode(@PathVariable int id ){
		Optional<Users> user= userdao.findById(id);
		
		if(user.isEmpty()) 
			throw new UserNotFoundException ("id"+id);
		EntityModel<Users> entityModel= EntityModel.of(user.get());
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass()).getAllUser());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
//	@GetMapping("/user/{id}")
//	public Users getOneUser(@PathVariable int id ){
//		Users user= userDao.findUser(id);
//		
//		if(user == null) 
//			throw new UserNotFoundException ("id"+id);
//		
//		
//		return user;
//	}
	
	@DeleteMapping("/jpa/user/{id}")
	public void DeleteUser(@PathVariable int id ){
		userdao.deleteById(id);
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
		Users savedUser = userdao.save(user);
		URI Location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(Location).build();
	}
	
	
	@GetMapping("/jpa/user/{id}/posts")
	public List<Post> getPostForUser(@PathVariable int id ){
	Optional<Users> user= userdao.findById(id);
		
		if(user.isEmpty()) 
			throw new UserNotFoundException ("id"+id);
		return user.get().getPosts();
		
	}
	
	@PostMapping("/jpa/user/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post ){
	Optional<Users> user= userdao.findById(id);
		
		if(user.isEmpty()) 
			throw new UserNotFoundException ("id"+id);
		post.setUser(user.get());
		
		Post savedPost= postdao.save(post);
		
		URI Location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId()).toUri();
		
		return ResponseEntity.created(Location).build();
		
	}

}
