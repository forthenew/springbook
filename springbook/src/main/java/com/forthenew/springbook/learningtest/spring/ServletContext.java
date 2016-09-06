package com.forthenew.springbook.learningtest.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.forthenew.springbook.learningtest.spring.web.hello.HelloController;
import com.forthenew.springbook.learningtest.spring.web.hello.HelloSpring;

@Configuration
@EnableWebMvc
public class ServletContext extends WebMvcConfigurerAdapter {

	@Bean
	public HelloSpring helloSpring(){
		HelloSpring helloSpring = new HelloSpring();
		return helloSpring;
	}
	
	@Bean(name="/hello")
	public HelloController hello(){
		HelloController helloController = new HelloController();
		return helloController;
	}
}
