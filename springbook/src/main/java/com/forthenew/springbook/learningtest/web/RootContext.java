package com.forthenew.springbook.learningtest.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootContext {

	@Bean
	public HelloSpring helloSpring(){
		HelloSpring helloSpring = new HelloSpring();
		return helloSpring;
	}
}
