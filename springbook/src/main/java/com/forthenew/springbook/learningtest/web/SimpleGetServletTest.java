package com.forthenew.springbook.learningtest.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class SimpleGetServletTest {

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		SimpleGetServlet servlet = new SimpleGetServlet();
		servlet.service(req, res);
		servlet.init();
		
		assertThat(res.getContentAsString(), is("<HTML><BODY>Hello Spring</BODY></HTML>"));
		assertThat(res.getContentAsString().indexOf("Hello Spring") > 0, is(true));
	}

}
