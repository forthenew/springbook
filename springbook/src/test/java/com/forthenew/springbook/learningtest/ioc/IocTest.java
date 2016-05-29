package com.forthenew.springbook.learningtest.ioc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.forthenew.springbook.learningtest.ioc.bean.AnnotatedHello;
import com.forthenew.springbook.learningtest.ioc.bean.Hello;
import com.forthenew.springbook.learningtest.ioc.bean.Printer;
import com.forthenew.springbook.learningtest.ioc.bean.StringPrinter;

public class IocTest {
	
	public String basePath = StringUtils.cleanPath(ClassUtils.classPackageAsResourcePath(getClass())) + "/";

	@Test
	public void testBeanEnroll() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerSingleton("hello", Hello.class);
		
		Hello hello1 = ac.getBean("hello", Hello.class);
		assertThat(hello1, is(notNullValue()));
		
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		ac.registerBeanDefinition("hello2", helloDef);
		
		Hello hello2 = ac.getBean("hello2", Hello.class);
		assertThat(hello2.sayHello(), is("Hello Spring"));
		
		assertThat(hello1, is(not(hello2)));
		
		assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
	}
	
	@Test
	public void registerBeanWithDependency() {
		StaticApplicationContext ac = new StaticApplicationContext();
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
		BeanDefinition helloDef = new RootBeanDefinition(Hello.class);
		helloDef.getPropertyValues().addPropertyValue("name", "Spring");
		helloDef.getPropertyValues().addPropertyValue("printer", new RuntimeBeanReference("printer"));
		ac.registerBeanDefinition("hello", helloDef);
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	}
	
	@Test
	public void genericApplicationContext() {
		GenericApplicationContext ac = new GenericApplicationContext();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
		reader.loadBeanDefinitions("com/forthenew/springbook/learningtest/ioc/IocTest-context.xml");
		ac.refresh();
		
		Hello hello = ac.getBean("hello", Hello.class);
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	}
	
	@Test
	public void parentChildContext() {
		ApplicationContext parent = new GenericXmlApplicationContext(basePath + "parentContext.xml");
		GenericApplicationContext child = new GenericApplicationContext(parent);
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
		reader.loadBeanDefinitions(basePath + "childContext.xml");
		child.refresh();
		Printer printer = child.getBean("printer", Printer.class);
		assertThat(printer, is(notNullValue()));
		
		Hello hello = child.getBean("hello", Hello.class);
		hello.print();
		assertThat(printer.toString(), is("Hello Child"));
	}
	
	@Test
	public void simpleBeanScanning() {
		ApplicationContext ac = new AnnotationConfigApplicationContext("com.forthenew.springbook.learningtest.ioc.bean");
		AnnotatedHello hello = ac.getBean("myAnnoatatedHello", AnnotatedHello.class);
		assertThat(hello, is(notNullValue()));
	}
	
	@Test
	public void simpleAtAutowired() {
		AbstractApplicationContext ac = new AnnotationConfigApplicationContext(BeanA.class, BeanB.class);
		BeanA beanA = ac.getBean(BeanA.class);
		assertThat(beanA.beanB, is(notNullValue()));
	}
	public static class BeanA {
		@Autowired
		BeanB beanB;
	}
	public static class BeanB{}
}
