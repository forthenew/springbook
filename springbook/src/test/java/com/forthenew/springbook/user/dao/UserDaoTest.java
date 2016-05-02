package com.forthenew.springbook.user.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.forthenew.springbook.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/spring/application-config.xml")
public class UserDaoTest {
//	@Autowired
//	private ApplicationContext context;
	
	@Autowired
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setup() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/application-config.xml");
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		dao = context.getBean("userDao", UserDao.class);
		user1 = new User("gyumee", "박성철", "springno1");
		user2 = new User("leegw700", "이길원", "springno2");
		user3 = new User("bumjin", "박범진", "springno3");
	}
	@Test
	public void addAndGet() throws SQLException {
		
		dao.deleteAll();
		assertThat(new Integer(0), is(dao.getCount()));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(new Integer(2), is(dao.getCount()));
		
		User userget1 = dao.get(user1.getId());
		assertThat(user1.getPassword(), is(userget1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertThat(user2.getPassword(), is(userget2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertThat(new Integer(0), is(dao.getCount()));
		
		dao.add(user1);
		assertThat(new Integer(1), is(dao.getCount()));
		
		dao.add(user2);
		assertThat(new Integer(2), is(dao.getCount()));
		
		dao.add(user3);
		assertThat(new Integer(3), is(dao.getCount()));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertThat(new Integer(0), is(dao.getCount()));

		dao.get("unkonwn_id");
	}
}
