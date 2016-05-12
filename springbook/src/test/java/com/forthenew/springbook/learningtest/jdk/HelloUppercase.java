package com.forthenew.springbook.learningtest.jdk;

public class HelloUppercase implements Hello {
	Hello hello;
	
	public HelloUppercase(Hello hello) {
		this.hello = hello;
	}

	@Override
	public String sayHello(String name) {
		return this.hello.sayHello(name).toUpperCase();
	}

	@Override
	public String sayHi(String name) {
		return this.hello.sayHi(name).toUpperCase();
	}

	@Override
	public String sayThankyou(String name) {
		return this.hello.sayThankyou(name).toUpperCase();
	}

}
