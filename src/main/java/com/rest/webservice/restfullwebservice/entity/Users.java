package com.rest.webservice.restfullwebservice.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="user_details")
public class Users {

	protected Users() {
		
	}
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=8, message = "name should be than 7 letter")
//	@JsonProperty("name")
	private String name;
	
	@Past(message="birthdate should be in past")
//	@JsonProperty("birthdate")
	private LocalDate birthdate;

	public Users(Integer id, String name, LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.birthdate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", birthdate=" + birthdate + "]";
	}
	
	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Post> posts;

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
