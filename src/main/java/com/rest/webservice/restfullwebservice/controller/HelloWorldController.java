package com.rest.webservice.restfullwebservice.controller;


import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rest.webservice.restfullwebservice.bean.HelloWorldBean;

@RestController
public class HelloWorldController {
	
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messagesource) {
		this.messageSource = messagesource;
	}

	@GetMapping("/hello")
	public static String func() {
		return "hello world";
	}
	
	@GetMapping("/hello-bean")
	public static HelloWorldBean funcHello() {
		return new HelloWorldBean("hello world");
	}
	
	@GetMapping("/hello-bean/path/{name}")
	public static HelloWorldBean funcHelloPathVar(@PathVariable String name) {
		return new HelloWorldBean(String.format("hello %s", name));
	}
	
	@GetMapping("/hello-internationalization")
	public String internationalizefunc() {
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "default message", locale);
//		return "hello world i18n";
	}
	
}
