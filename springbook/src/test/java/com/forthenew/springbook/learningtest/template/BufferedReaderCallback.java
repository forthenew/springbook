package com.forthenew.springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {

	Integer somethingWithReader(BufferedReader br) throws IOException;
}
