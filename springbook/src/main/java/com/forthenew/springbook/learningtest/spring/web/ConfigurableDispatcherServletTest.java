package com.forthenew.springbook.learningtest.spring.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.ModelAndView;

import com.forthenew.springbook.learningtest.spring.ServletContext;
import com.forthenew.springbook.learningtest.spring.web.hello.HelloController;

public class ConfigurableDispatcherServletTest {

	@Test
	public void test1() throws ServletException, IOException {
		ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();
		servlet.setClasses(new Class[]{ServletContext.class});
		servlet.init(new MockServletConfig("spring"));
		
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		servlet.service(req, res);
		
		ModelAndView mav = servlet.getModelAndView();
		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
	}

	@Test
	public void test2() throws Exception{
		ApplicationContext ac = new AnnotationConfigApplicationContext(ServletContext.class);
		HelloController helloController = ac.getBean(HelloController.class);
		
		MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
		req.addParameter("name", "Spring");
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		ModelAndView mav = helloController.handleRequest(req, res);
		
		assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
		assertThat((String)mav.getModel().get("message"), is("Hello Spring"));
	}
}
