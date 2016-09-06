package com.forthenew.springbook.learningtest.spring.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractDispatcherServletTest implements AfterRunService {

	protected	MockHttpServletRequest			request;
	protected	MockHttpServletResponse			response;
	protected	MockServletConfig					config		= new MockServletConfig("spring");
	protected	MockHttpSession					session;
	
	private	ConfigurableDispatcherServlet	dispatcherServlet;
	private	Class<?>[]							classes;
	private	String[]							locations;
	private	String[]							relativeLocations;
	private	String								servletPath;
	
	public AbstractDispatcherServletTest setClasses(Class<?>[] classes) {
		this.classes = classes;
		return this;
	}

	public AbstractDispatcherServletTest setLocations(String[] locations) {
		this.locations = locations;
		return this;
	}

	public AbstractDispatcherServletTest setRelativeLocations(String[] relativeLocations) {
		this.relativeLocations = relativeLocations;
		return this;
	}

	public AbstractDispatcherServletTest setServletPath(String servletPath) {
		if(this.request == null)
			this.servletPath = servletPath;
		else
			this.request.setServletPath(servletPath);
		return this;
	}
	
	public AbstractDispatcherServletTest initRequest(String requestUri, String method){
		this.request	= new MockHttpServletRequest(method, requestUri);
		this.response	= new MockHttpServletResponse();
		if(this.servletPath != null) this.setServletPath(this.servletPath);
		return this;
	}
	
	public AbstractDispatcherServletTest initRequest(String requestUri, RequestMethod method){
		return this.initRequest(requestUri, method.toString());
	}
	
	public AbstractDispatcherServletTest initRequest(String requestUri){
		initRequest(requestUri, RequestMethod.GET);
		return this;
	}
	
	public AbstractDispatcherServletTest addParameter(String name, String value){
		if(this.request == null)
			throw new IllegalStateException("request가 초기화되지 않았습니다.");
		this.request.addParameter(name, value);
		return this;
	}
	
	public AbstractDispatcherServletTest buildDispatcherServlet() throws ServletException{
		if(this.classes == null && this.locations == null && this.relativeLocations == null)
			throw new IllegalStateException("classes와 locations 중 하나는 설정해야 합니다.");
		this.dispatcherServlet = new ConfigurableDispatcherServlet();
		this.dispatcherServlet.setClasses(this.classes);
		this.dispatcherServlet.setLocations(this.locations);
		if(this.relativeLocations != null) this.dispatcherServlet.setRelativeLocations(getClass(), this.relativeLocations);
		this.dispatcherServlet.init(this.config);
		return this;
	}

	@Override
	public String getContentAsString() throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebApplicationContext getContext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getBean(Class<T> beanType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView getModelAndView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AfterRunService assertViewName(String viewName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AfterRunService assertModel(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
