package com.forthenew.springbook.learningtest.ioc;

public class ConsolePrinter implements Printer {

	@Override
	public void print(String message) {
		System.out.println(message);
	}

}
