package com.forthenew.springbook.learningtest.factorybean;

public class Message {
	String text;
	
	private Message(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public static Message newInstance(String text) {
		return new Message(text);
	}
}
