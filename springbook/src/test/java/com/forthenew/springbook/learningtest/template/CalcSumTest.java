package com.forthenew.springbook.learningtest.template;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	Calculator calculator;
	String numFilePath;
	@Before
	public void setup() {
		this.calculator = new Calculator();
		this.numFilePath = getClass().getResource("numbers.txt").getPath();
	}

	@Test
	public void sumOfNumbers() throws IOException {
		int sum = calculator.calcSum(this.numFilePath);
		assertThat(sum, is(10));
	}
	@Test
	public void multiplyOfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(this.numFilePath), is(24));
	}
	@Test
	public void concatenate() throws IOException {
		assertThat(calculator.concatenate(this.numFilePath), is("1234"));
	}
}
