package com.forthenew.springbook.learningtest.ioc.bean;

import org.springframework.stereotype.Component;

@Component("myAnnoatatedHello")
public class AnnotatedHello {
	String name;
	Printer printer;
	public void setName(String name) {
		this.name = name;
	}
	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	
	public String sayHello() {
		return "Hello " + this.name;
	}
	public void print() {
		this.printer.print(sayHello());
	}
}
