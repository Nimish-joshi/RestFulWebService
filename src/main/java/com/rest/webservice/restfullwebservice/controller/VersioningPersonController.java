package com.rest.webservice.restfullwebservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfullwebservice.entity.Name;
import com.rest.webservice.restfullwebservice.entity.PersonV1;
import com.rest.webservice.restfullwebservice.entity.PersonV2;

@RestController
public class VersioningPersonController {

	// below two are simple versioning of api mapping 
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionPerson() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionPerson() {
		return new PersonV2(new Name("Nimish","joshi"));
	}
	
	// below two are mapping with requestParameters 
	// uri versioning - used by twitter 

	@GetMapping(path="/person",params="version1")
	public PersonV1 getFirstVersionOfRequestParameter() {
		return new PersonV1("nimish joshi");
	}
	
	@GetMapping(path="/person",params="version2")
	public PersonV2 getSecondVersionOfRequestParam() {
		return new PersonV2(new Name("Nimish","joshi"));
	}
	
	// below one is the request header
	//custom header versioning
	
	@GetMapping(path="/person/header",headers="X-API-VERSION=1")
	public PersonV1 getFirstVersionOfRequestHeader() {
		return new PersonV1("Bob Charlie");
	}
	
	// accept header aka media type versioning - used by microsoft
	@GetMapping(path="/person/accept",produces="application/vnd.company.app-v1+json")
	public PersonV2 getSecondVersionOfAcceptHeader() {
		return new PersonV2(new Name("Nimish","joshi"));
	}
}
