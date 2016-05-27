package com.forthenew.springbook.learningtest.ioc;

public class Hello {
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
