package com.rest.webservice.restfullwebservice.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.rest.webservice.restfullwebservice.entity.Users;


@Component
public class UserDaoService {

	private static List<Users> user =new ArrayList<>();
	
	private static int userCount =0;
	
	static {
		user.add(new Users(++userCount,"nimish",LocalDate.now().minusYears(24)));
		user.add(new Users(++userCount,"parth",LocalDate.now().minusYears(28)));
		user.add(new Users(++userCount,"bhavita",LocalDate.now().minusYears(31)));
	}

	public List<Users> findAll(){
		return user;
	}
	
	public Users findUser(int id){
		Predicate<? super Users> predicate = user ->user.getId().equals(id);
		return user.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void DeleteUserByID(int id){
		Predicate<? super Users> predicate = user ->user.getId().equals(id);
		user.removeIf(predicate);
	}
	
	public Users save(Users users) {
		users.setId(++userCount);
		user.add(users);
		return users;
	}
}
